package br.com.estudo.session.implementations;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.annotation.ejb.LocalBinding;
import org.jboss.annotation.ejb.RemoteBinding;

import br.com.estudo.dao.DAOFactory;
import br.com.estudo.dao.interfaces.IDAOContato;
import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.entities.Contato;
import br.com.estudo.exception.BussinessException;
import br.com.estudo.helper.ContatoHelper;
import br.com.estudo.session.interfaces.ContatoSession;
import br.com.estudo.session.interfaces.local.ContatoSessionLocal;
import br.com.estudo.session.interfaces.remote.ContatoSessionRemote;

@Stateless
@RemoteBinding(jndiBinding = ContatoSession.JNDI_NAME)
@LocalBinding(jndiBinding = ContatoSession.LOCAL_JNDI_NAME)
public class ContatoSessionBean implements ContatoSessionLocal,
		ContatoSessionRemote {

	private static final Logger log = Logger.getLogger(ContatoSessionBean.class
			.getName());
	@PersistenceContext
	EntityManager manager;

	public void save(ContatoDTO contato) throws BussinessException {
		try {
			IDAOContato dao = DAOFactory.getFactory(manager).getDAOContato();
			Contato entity = ContatoHelper.getInstance().createEntity(contato);
			entity.setId(null);
			dao.save(entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new BussinessException(e);
		}
	}
	
	public List<ContatoDTO> readByUser(Integer idUser) throws BussinessException {
		try {
			IDAOContato dao = DAOFactory.getFactory(manager).getDAOContato();
			List<Contato> entities = dao.readByUser(idUser);
			return ContatoHelper.getInstance().createDTO(entities);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new BussinessException(e);
		}
	}
}
