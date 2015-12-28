package br.com.estudo.dao;

import javax.persistence.EntityManager;

import br.com.estudo.dao.implementations.DAOContato;
import br.com.estudo.dao.implementations.DAOUsuario;
import br.com.estudo.dao.interfaces.IDAOContato;
import br.com.estudo.dao.interfaces.IDAOUsuario;
import br.com.estudo.exception.DAOException;

public class DAOFactory {

	static EntityManager myManager;

	private static DAOFactory factory = null;

	public static final DAOFactory getFactory(EntityManager manager) {
		if (factory == null)
			factory = new DAOFactory();
		myManager = manager;
		return factory;
	}

	public IDAOUsuario getDAOUsuario() throws DAOException {
		return new DAOUsuario(myManager);
	}
	
	public IDAOContato getDAOContato() throws DAOException {
		return new DAOContato(myManager);
	}
}
