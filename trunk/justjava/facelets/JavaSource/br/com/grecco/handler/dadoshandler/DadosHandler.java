/**
 *
 */
package br.com.grecco.handler.dadoshandler;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.hibernate.Session;

import br.com.grecco.dados.Dados;
import br.com.justjava.sgv.dao.DAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

/**
 * @author OTTA956
 *
 */
public class DadosHandler {

	private Dados dados = new Dados();
	private long sequence = 0;

	public long getSequence() {
		return sequence ;
	}

	public void salvar() {

		Session session = HibernateUtil.getCurrentSession();
		DAO<Dados> dao = new DAO<Dados>(session, Dados.class);
		if (dados.getId() == 0) {
			dao.salveOrUpdate(this.dados);
		} else {
			session.merge(this.dados);
		}
		this.dados = new Dados();

	}

	public void excluir() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<Dados> dao = new DAO<Dados>(session, Dados.class);
		dao.deleta(this.dados);
	}

	public void selecionaDados() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<Dados> dao = new DAO<Dados>(session, Dados.class);
		dao.load((long) dados.getId());
	}

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public List<Dados> getAllDados() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<Dados> dao = new DAO<Dados>(session, Dados.class);
		return dao.list();
	}

	public void carregaDados(ActionEvent event) {
		/*
		 * Usado para ler em um Array UIComponent comandLink =
		 * event.getComponent(); UIParameter parameter = (UIParameter)
		 * comandLink .findComponent("editId"); Long id = (Long)
		 * parameter.getValue(); this.fornecedor =
		 * this.fornecedores.get(id.intValue());
		 */
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<Dados> dao = new DAO<Dados>(session, Dados.class);
		this.dados = dao.load(id);


	}

}
