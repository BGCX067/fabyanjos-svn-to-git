package br.com.estudo.session.implementations;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.annotation.ejb.LocalBinding;
import org.jboss.annotation.ejb.RemoteBinding;

import br.com.estudo.dao.DAOFactory;
import br.com.estudo.dao.interfaces.IDAOUsuario;
import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.entities.Usuario;
import br.com.estudo.exception.BussinessException;
import br.com.estudo.helper.UsuarioHelper;
import br.com.estudo.session.interfaces.UsuarioSession;
import br.com.estudo.session.interfaces.local.UsuarioSessionLocal;
import br.com.estudo.session.interfaces.remote.UsuarioSessionRemote;

@Stateless
@RemoteBinding(jndiBinding = UsuarioSession.JNDI_NAME)
@LocalBinding(jndiBinding = UsuarioSession.LOCAL_JNDI_NAME)
public class UsuarioSessionBean implements UsuarioSessionLocal, UsuarioSessionRemote {
	
	private static final Logger log = Logger.getLogger(UsuarioSessionBean.class
			.getName());
	@PersistenceContext	EntityManager manager;
	
	public void save(UsuarioDTO usuario) throws BussinessException {
		try {
			IDAOUsuario dao = DAOFactory.getFactory(manager).getDAOUsuario();
			Usuario entity = UsuarioHelper.getInstance().createEntity(usuario); 
			entity.setId(null);
			dao.save(entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new BussinessException(e);
		}		
	}

	public UsuarioDTO readByLoginPassword(String login, String password)
			throws BussinessException {
		try {
			IDAOUsuario dao = DAOFactory.getFactory(manager).getDAOUsuario();
			Usuario entity = dao.readByLoginPassword(login, password);
			UsuarioDTO usuario = null;
			if(entity != null)
				usuario = UsuarioHelper.getInstance().createDTO(entity); 
			return usuario; 
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new BussinessException(e);
		}
	}
}
