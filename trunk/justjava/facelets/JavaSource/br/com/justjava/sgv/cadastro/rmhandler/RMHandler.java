/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.rmhandler;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.rm.CadastroRM;
import br.com.justjava.sgv.dao.DAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

public class RMHandler {

	private CadastroRM cadastroRM = new CadastroRM();

	/*
	 * private long sequence = 0;
	 *
	 * public long getSequence() { return sequence ; }
	 */
	public void salvar() {

		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		if (cadastroRM.getId() == 0) {
			dao.salveOrUpdate(this.cadastroRM);
		} else {
			session.merge(this.cadastroRM);
		}
		this.cadastroRM = new CadastroRM();

	}

	public void excluir() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		dao.deleta(this.cadastroRM);
	}

	public void selecionaDados() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		dao.load((long) cadastroRM.getId());
	}

	public CadastroRM getCadastroRM() {
		return cadastroRM;
	}

	public void setCadastroRM(CadastroRM cadastroRM) {
		this.cadastroRM = cadastroRM;
	}

	public List<CadastroRM> getAllCadastroRM() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		return dao.list();
	}

	public void carregaCadastroRM(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		this.cadastroRM = dao.load(id);

	}

	public void excluirCadastroRM(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> dao = new DAO<CadastroRM>(session, CadastroRM.class);
		this.cadastroRM = dao.load(id);
		dao.deleta(cadastroRM);
		this.cadastroRM = new CadastroRM();

	}

}
