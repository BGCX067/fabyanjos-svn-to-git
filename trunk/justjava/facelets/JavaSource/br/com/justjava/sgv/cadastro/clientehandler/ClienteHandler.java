/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.clientehandler;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.cliente.CadastroCliente;
import br.com.justjava.sgv.dao.DAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

public class ClienteHandler {

	private CadastroCliente cadastroCliente = new CadastroCliente();

	/*
	 * private long sequence = 0;
	 *
	 * public long getSequence() { return sequence ; }
	 */
	public void salvar() {

		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		if (cadastroCliente.getId() == 0) {
			dao.salveOrUpdate(this.cadastroCliente);
		} else {
			session.merge(this.cadastroCliente);
		}
		this.cadastroCliente = new CadastroCliente();

	}

	public void excluir() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		dao.deleta(this.cadastroCliente);
	}

	public void selecionaDados() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		dao.load((long) cadastroCliente.getId());
	}

	public CadastroCliente getCadastroCliente() {
		return cadastroCliente;
	}

	public void setCadastroCliente(CadastroCliente cadastroVisita) {
		this.cadastroCliente = cadastroVisita;
	}

	public List<CadastroCliente> getAllCadastroCliente() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		return dao.list();
	}

	public void carregaCadastroCliente(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		this.cadastroCliente = dao.load(id);

	}

	public void excluirCadastroCliente(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroCliente> dao = new DAO<CadastroCliente>(session,
				CadastroCliente.class);
		this.cadastroCliente = dao.load(id);
		dao.deleta(cadastroCliente);
		this.cadastroCliente = new CadastroCliente();

	}

}
