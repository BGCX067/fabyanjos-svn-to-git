package br.com.estudo.delegate;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.exception.BussinessException;
import br.com.estudo.exception.DelegateException;
import br.com.estudo.exception.ServiceLocatorException;
import br.com.estudo.service.ServiceLocator;
import br.com.estudo.session.interfaces.UsuarioSession;

public class UsuarioDelegate {

	private static final Logger log = Logger.getLogger(UsuarioDelegate.class.getName());
	private static UsuarioDelegate instance = null;
	private UsuarioSession session = null;

	private UsuarioDelegate() throws DelegateException {
		try {
			this.session = (UsuarioSession) ServiceLocator.getInstance()
					.getHome(UsuarioSession.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}

	public static UsuarioDelegate getInstance() throws DelegateException  {
		if (instance == null)
			instance = new UsuarioDelegate();
		return instance;
	}
	
	public void save(UsuarioDTO usuario) throws DelegateException {
		try {
			session.save(usuario);
		} catch (BussinessException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}
	
	public UsuarioDTO login(String login, String password) throws DelegateException {
		try {
			return session.readByLoginPassword(login, password);
		} catch (BussinessException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new DelegateException(e);
		}
	}
}
