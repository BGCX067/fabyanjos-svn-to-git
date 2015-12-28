package br.com.estudo.dao.interfaces;

import java.util.List;

import br.com.estudo.entities.Contato;
import br.com.estudo.exception.DAOException;

public interface IDAOContato {
	
	public void save(Contato contato) throws DAOException;
	public List<Contato> readByUser(Integer idUser) throws DAOException;

}
