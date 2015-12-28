/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.campanhahandler;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.campanha.CadastroCampanha;
import br.com.justjava.sgv.dao.DAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

public class CampanhaHandler {

	private CadastroCampanha cadastroCampanha = new CadastroCampanha();

	/*
	 * private long sequence = 0;
	 *
	 * public long getSequence() { return sequence ; }
	 */
	public void salvar() {

		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		if (cadastroCampanha.getId() == 0) {
			dao.salveOrUpdate(this.cadastroCampanha);
		} else {
			session.merge(this.cadastroCampanha);
		}
		this.cadastroCampanha = new CadastroCampanha();

	}

	public void excluir() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		dao.deleta(this.cadastroCampanha);
	}

	public void selecionaDados() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		dao.load((long) cadastroCampanha.getId());
	}

	public CadastroCampanha getCadastroCampanha() {
		return cadastroCampanha;
	}

	public void setCadastroCampanha(CadastroCampanha cadastroCampanha) {
		this.cadastroCampanha = cadastroCampanha;
	}

	public List<CadastroCampanha> getAllCadastroCampanha() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		return dao.list();
	}

	public void carregaCadastroCampanha(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		this.cadastroCampanha = dao.load(id);

	}

	public void excluirCadastroCliente(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCampanha> dao = new DAO<CadastroCampanha>(session,
				CadastroCampanha.class);
		this.cadastroCampanha = dao.load(id);
		dao.deleta(cadastroCampanha);
		this.cadastroCampanha = new CadastroCampanha();

	}

}
