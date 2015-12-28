package br.com.estudo.dao.implementations;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.estudo.dao.interfaces.IDAOUsuario;
import br.com.estudo.entities.Usuario;
import br.com.estudo.exception.DAOException;

public class DAOUsuario implements IDAOUsuario {

	private EntityManager manager;

	public DAOUsuario(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Usuario usuario) throws DAOException {
		manager.persist(usuario);
	}

	public Usuario readByLoginPassword(String login, String password) throws DAOException {
		StringBuffer sql = new StringBuffer();
		
		sql
			.append("FROM Usuario u ")
			.append("WHERE u.email = :login ")
			.append("AND u.password = :password");
		
		Query query = manager.createQuery(sql.toString());
		query.setParameter("login", login);
		query.setParameter("password", password);
		
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
