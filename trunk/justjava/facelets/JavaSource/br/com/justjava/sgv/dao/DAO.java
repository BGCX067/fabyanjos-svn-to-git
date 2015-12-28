package br.com.justjava.sgv.dao;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;


/**
 * @author wav138
 *
 */
public class DAO<T> {
	// T = Type ou seja tipo
	private Session session;

	private Logger logger = Logger.getLogger(DAO.class);

	// criado p/ pegar a classe q vai no método load e generics q nao permite
	// chamar atributos e metodos
	private Class classe;

	public DAO(Session session, Class classe) {
		this.session = session;
		this.classe = classe;
	}


	@SuppressWarnings("unchecked")
	public void save(T t) {
		logger.info("salvando " + t);
		session.save(t);
	}

	@SuppressWarnings("unchecked")
	public T load(Long id) {
		logger.info("lendo " + classe + " id: "+id);
		return (T) session.load(classe, id);
	}

	@SuppressWarnings("unchecked")
	public void salveOrUpdate(T t) {
		session.saveOrUpdate(t);
	}

	public void deleta(T t) {
		logger.info("deletando " + t);
		session.delete(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		// Critéria API
		Criteria criteria = getSession().createCriteria(classe);
		return criteria.list();
	}

//	 Criado p/ as outras classes conseguirem ver
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}



	/*public List<String> buscaNomeFornecedor(String buscaNomeFornecedor){
		Criteria criteria = session.createCriteria(Fornecedor.class);
		criteria.add(Restrictions.ilike("nome", buscaNomeFornecedor + "%"));
		criteria.addOrder(Order.asc("nome"));
		criteria.setProjection(Projections.property("nome"));
		return criteria.list();


	}*/
}
