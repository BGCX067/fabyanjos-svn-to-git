package br.com.estudo.dao.interfaces;

import br.com.estudo.entities.Usuario;
import br.com.estudo.exception.DAOException;

public interface IDAOUsuario {
	
	public void save(Usuario usuario) throws DAOException;
	public Usuario readByLoginPassword(String login, String password) throws DAOException;

}
