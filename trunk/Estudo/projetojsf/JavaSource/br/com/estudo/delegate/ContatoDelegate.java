package br.com.estudo.delegate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.exception.BussinessException;
import br.com.estudo.exception.DelegateException;
import br.com.estudo.exception.ServiceLocatorException;
import br.com.estudo.service.ServiceLocator;
import br.com.estudo.session.interfaces.ContatoSession;

public class ContatoDelegate {

	private static final Logger log = Logger.getLogger(ContatoDelegate.class
			.getName());
	private static ContatoDelegate instance = null;
	private ContatoSession session = null;

	private ContatoDelegate() throws DelegateException {
		try {
			this.session = (ContatoSession) ServiceLocator.getInstance()
					.getHome(ContatoSession.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}

	public static ContatoDelegate getInstance() throws DelegateException {
		if (instance == null)
			instance = new ContatoDelegate();
		return instance;
	}

	public void save(ContatoDTO contato) throws DelegateException {
		try {
			session.save(contato);
		} catch (BussinessException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}
	
	public List<ContatoDTO> readByUser(Integer idUser) throws DelegateException {
		try {
			return session.readByUser(idUser);
		} catch (BussinessException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}
}
