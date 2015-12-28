package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Cliente;

public class ClienteDAO {
	private HashMap<Long, Cliente> clientes;
	private HashMap<Long, Cliente> lixo;
	private Long id = 1L;
	
	public ClienteDAO() {
		clientes = new HashMap<Long, Cliente>();
		lixo = new HashMap<Long, Cliente>();
	}
	
	public Long save(Cliente cliente) {
		if(cliente.getId() == null || cliente.getId() == 0){
			cliente.setId(id++);
		}
		clientes.put(cliente.getId(), cliente);
		return cliente.getId();
	}
	public Cliente load(Long key) {
		return clientes.get(key);
	}
	public List<Cliente> listAll(){
		List<Cliente> list = new ArrayList<Cliente>(clientes.values());
		return list;
	}
	public boolean delete(Cliente cliente){
		lixo.put(cliente.getId(),clientes.remove(cliente.getId()));
		return true;
	}
	@PostConstruct
	public void criaDados(){
		Cliente cliente = new Cliente("TCC");
		save(cliente);
		
		cliente = new Cliente("Empreendedorismo");
		save(cliente);
		
		cliente = new Cliente("IA");
		save(cliente);
	}

}
