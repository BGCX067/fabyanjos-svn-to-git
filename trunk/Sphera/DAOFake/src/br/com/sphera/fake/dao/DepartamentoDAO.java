package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sphera.entities.Departamento;

public class DepartamentoDAO {
	private HashMap<Long, Departamento> departamentos;
	private HashMap<Long, Departamento> lixo;
	private Long id = 1L;
	
	public DepartamentoDAO() {
		departamentos = new HashMap<Long, Departamento>();
		lixo = new HashMap<Long, Departamento>();
		criaDados();
	}
	
	public Long save(Departamento projeto) {
		if(projeto.getId() == null || projeto.getId() == 0){
			projeto.setId(id++);
		}
		departamentos.put(projeto.getId(), projeto);
		return projeto.getId();
	}
	public Departamento load(Long key) {
		return departamentos.get(key);
	}
	public List<Departamento> listAll(){
		List<Departamento> list = new ArrayList<Departamento>(departamentos.values());
		return list;
	}
	public boolean delete(Departamento projeto){
		lixo.put(projeto.getId(),departamentos.remove(projeto.getId()));
		return true;
	}

	private void criaDados(){
		for (int i = 1; i < 10; i++) {
			this.save(new Departamento("Departamento "+i));
		}
		
		
	}
}
