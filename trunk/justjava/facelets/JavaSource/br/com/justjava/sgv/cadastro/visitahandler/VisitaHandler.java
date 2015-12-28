/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.visitahandler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.VariableResolver;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.campanha.CadastroCampanha;
import br.com.justjava.sgv.cadastro.campanhahandler.CampanhaHandler;
import br.com.justjava.sgv.cadastro.cliente.CadastroCliente;
import br.com.justjava.sgv.cadastro.clientehandler.ClienteHandler;
import br.com.justjava.sgv.cadastro.rm.CadastroRM;
import br.com.justjava.sgv.cadastro.rmhandler.RMHandler;
import br.com.justjava.sgv.cadastro.visita.CadastroVisita;
import br.com.justjava.sgv.dao.DAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

public class VisitaHandler {

	private CadastroVisita cadastroVisita = new CadastroVisita();

	private HtmlSelectOneMenu htmlSelectOneMenuRM;

	private HtmlSelectOneMenu htmlSelectOneMenuCliente;

	private HtmlSelectOneMenu htmlSelectOneMenuCampanha;
	private String teste;
	public CadastroVisita getCadastroVisita() {
		return cadastroVisita;
	}

	public void setCadastroVisita(CadastroVisita cadastroVisita) {
		this.cadastroVisita = cadastroVisita;
	}

	// Cliente
	public List<SelectItem> getClientes() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<CadastroCliente> cadastroClienteList = getClienteHandler()
				.getAllCadastroCliente();
		for (CadastroCliente cadastroCliente : cadastroClienteList) {
			// Só aceita String por isso estou convertendo o long do id com o
			SelectItem selectItem = new SelectItem(String
					.valueOf(cadastroCliente.getId()), cadastroCliente
					.getNomeCliente());
			lista.add(selectItem);
		}

		return lista;

	}

	// Pega a lista criada sem precisar dar new no FornecedorHandler do método
	private ClienteHandler getClienteHandler() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		VariableResolver variableResolver = facesContext.getApplication()
				.getVariableResolver();
		// Tenho que passar o facesContext e também o managedBean
		return (ClienteHandler) variableResolver.resolveVariable(facesContext,
				"clienteHandler");

	}

	// Campanha
	public List<SelectItem> getCampanhas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<CadastroCampanha> cadastroCampanhaList = getCampanhaHandler()
				.getAllCadastroCampanha();
		for (CadastroCampanha cadastroCampanha : cadastroCampanhaList) {
			// Só aceita String por isso estou convertendo o long do id com o
			SelectItem selectItem = new SelectItem(String
					.valueOf(cadastroCampanha.getId()), cadastroCampanha
					.getNomeCampanha());
			lista.add(selectItem);
		}

		return lista;

	}

	// Pega a lista criada sem precisar dar new no FornecedorHandler do método
	private CampanhaHandler getCampanhaHandler() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		VariableResolver variableResolver = facesContext.getApplication()
				.getVariableResolver();
		// Tenho que passar o facesContext e também o managedBean
		return (CampanhaHandler) variableResolver.resolveVariable(facesContext,
				"campanhaHandler");

	}

	// RM
	public List<SelectItem> getRMS() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<CadastroRM> cadastroRMList = getRMHandler().getAllCadastroRM();
		for (CadastroRM cadastroRM : cadastroRMList) {
			// Só aceita String por isso estou convertendo o long do id com o
			SelectItem selectItem = new SelectItem(String.valueOf(cadastroRM
					.getId()), cadastroRM.getNomeRM());
			lista.add(selectItem);
		}

		return lista;

	}

	// Pega a lista criada sem precisar dar new no FornecedorHandler do método
	private RMHandler getRMHandler() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		VariableResolver variableResolver = facesContext.getApplication()
				.getVariableResolver();
		// Tenho que passar o facesContext e também o managedBean
		return (RMHandler) variableResolver.resolveVariable(facesContext,
				"rmHandler");

	}

	public void salvar() {

		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> cadastroRMDao = new DAO<CadastroRM>(session,
				CadastroRM.class);
		long idRM = Long.parseLong(htmlSelectOneMenuRM.getValue().toString());
		CadastroRM cadastroRM = cadastroRMDao.load(idRM);

		DAO<CadastroCliente> cadastroClienteDao = new DAO<CadastroCliente>(
				session, CadastroCliente.class);
		long idCliente = Long.parseLong(htmlSelectOneMenuCliente.getValue()
				.toString());
		CadastroCliente cadastroCliente = cadastroClienteDao.load(idCliente);

		DAO<CadastroCampanha> cadastroCampanhaDao = new DAO<CadastroCampanha>(
				session, CadastroCampanha.class);
		long idCampanha = Long.parseLong(htmlSelectOneMenuCampanha.getValue()
				.toString());
		CadastroCampanha cadastroCampanha = cadastroCampanhaDao
				.load(idCampanha);

		cadastroVisita.setCadastroRM(cadastroRM);
		cadastroVisita.setCadastroCliente(cadastroCliente);
		cadastroVisita.setCadastroCampanha(cadastroCampanha);
		DAO<CadastroVisita> dao = new DAO<CadastroVisita>(session,
				CadastroVisita.class);
		if (cadastroVisita.getId() == 0) {
			dao.salveOrUpdate(this.cadastroVisita);
		} else {
			session.merge(this.cadastroVisita);
		}
		this.cadastroVisita = new CadastroVisita();

	}

	public void salvar(ActionEvent actionEvent) {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroRM> cadastroRMDao = new DAO<CadastroRM>(session,
				CadastroRM.class);
		long idRM = Long.parseLong(htmlSelectOneMenuRM.getValue().toString());
		CadastroRM cadastroRM = cadastroRMDao.load(idRM);

		DAO<CadastroCliente> cadastroClienteDao = new DAO<CadastroCliente>(
				session, CadastroCliente.class);
		long idCliente = Long.parseLong(htmlSelectOneMenuCliente.getValue()
				.toString());
		CadastroCliente cadastroCliente = cadastroClienteDao.load(idCliente);

		DAO<CadastroCampanha> cadastroCampanhaDao = new DAO<CadastroCampanha>(
				session, CadastroCampanha.class);
		long idCampanha = Long.parseLong(htmlSelectOneMenuCampanha.getValue()
				.toString());
		CadastroCampanha cadastroCampanha = cadastroCampanhaDao
				.load(idCampanha);

		cadastroVisita.setCadastroRM(cadastroRM);
		cadastroVisita.setCadastroCliente(cadastroCliente);
		cadastroVisita.setCadastroCampanha(cadastroCampanha);
		DAO<CadastroVisita> dao = new DAO<CadastroVisita>(session,
				CadastroVisita.class);

		dao.salveOrUpdate(this.cadastroVisita);
		this.cadastroVisita = new CadastroVisita();
	}

	public List<CadastroVisita> getAllVisitas() {
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroVisita> dao = new DAO<CadastroVisita>(session,
				CadastroVisita.class);

		return dao.list();
	}

	public void carregaCadastroVisita(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroVisita> dao = new DAO<CadastroVisita>(session,
				CadastroVisita.class);
		this.cadastroVisita = dao.load(id);

	}

	public void excluirCadastroVisita(ActionEvent event) {
		UIComponent comandLink = event.getComponent();
		UIParameter parameter = (UIParameter) comandLink
				.findComponent("editId");
		Long id = (Long) parameter.getValue();
		Session session = HibernateUtil.getCurrentSession();
		DAO<CadastroVisita> dao = new DAO<CadastroVisita>(session,
				CadastroVisita.class);
		this.cadastroVisita = dao.load(id);
		dao.deleta(cadastroVisita);
		this.cadastroVisita = new CadastroVisita();

	}

	public HtmlSelectOneMenu getHtmlSelectOneMenuRM() {
		return htmlSelectOneMenuRM;
	}

	public void setHtmlSelectOneMenuRM(HtmlSelectOneMenu htmlSelectOneMenuRM) {
		this.htmlSelectOneMenuRM = htmlSelectOneMenuRM;
	}

	public HtmlSelectOneMenu getHtmlSelectOneMenuCliente() {
		return htmlSelectOneMenuCliente;
	}

	public void setHtmlSelectOneMenuCliente(
			HtmlSelectOneMenu htmlSelectOneMenuCliente) {
		this.htmlSelectOneMenuCliente = htmlSelectOneMenuCliente;
	}

	public HtmlSelectOneMenu getHtmlSelectOneMenuCampanha() {
		return htmlSelectOneMenuCampanha;
	}

	public void setHtmlSelectOneMenuCampanha(
			HtmlSelectOneMenu htmlSelectOneMenuCampanha) {
		this.htmlSelectOneMenuCampanha = htmlSelectOneMenuCampanha;
	}

}
