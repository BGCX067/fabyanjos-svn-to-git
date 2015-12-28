package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sphera.entities.Cliente;
import br.com.sphera.fake.dao.ClienteDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.util.JSFUtil;

public class ClienteController extends AbstractCrudBean {

	private Cliente cliente;
	private List<Cliente> clientes;
	private ClienteDAO dao;

	public ClienteController() {
		cliente = new Cliente();
	}

	public List<Cliente> getClientes() {
		if (clientes == null)
			clientes = dao.listAll();
		return clientes;
	}

	public List<SelectItem> getComboList() {
		List<Cliente> clients = getClientes();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (Cliente client : clients) {
			itens.add(new SelectItem(client, client.getNome()));
		}
		return itens;
	}

	public void setDao(ClienteDAO dao) {
		this.dao = dao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String delete() {
		dao.delete(cliente);
		JSFUtil.addInfoMessage("sucessDeletedClient", null);
		return null;
	}

	public String list() {
		return "clientes";
	}

	public String save() {
		dao.save(cliente);
		JSFUtil.addInfoMessage("sucessSavedClient", null);
		return "clientes";
	}

}
