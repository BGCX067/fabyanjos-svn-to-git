package br.com.estudo.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.estudo.dao.interfaces.IDAOContato;
import br.com.estudo.entities.Contato;
import br.com.estudo.exception.DAOException;

public class DAOContato implements IDAOContato {

	private EntityManager manager;

	public DAOContato(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Contato contato) throws DAOException {
		manager.persist(contato);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> readByUser(Integer idUser) throws DAOException {
		StringBuffer sql = new StringBuffer();
		
		sql
			.append("FROM Contato c ")
			.append("WHERE c.usuario.id = :idUser ");
		
		Query query = manager.createQuery(sql.toString());
		query.setParameter("idUser", idUser);
		
		return query.getResultList();

	}
}
