package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Cargo;


public class CargoDAO {
	private HashMap<Long, Cargo> cargos;
	private HashMap<Long, Cargo> lixo;
	private Long id = 1L;
	
	public CargoDAO() {
		cargos = new HashMap<Long, Cargo>();
		lixo = new HashMap<Long, Cargo>();
		
	}
	
	public Long save(Cargo projeto) {
		if(projeto.getId() == null || projeto.getId() == 0){
			projeto.setId(id++);
		}
		cargos.put(projeto.getId(), projeto);
		return projeto.getId();
	}
	public Cargo load(Long key) {
		return cargos.get(key);
	}
	public List<Cargo> listAll(){
		List<Cargo> list = new ArrayList<Cargo>(cargos.values());
		return list;
	}
	public boolean delete(Cargo projeto){
		lixo.put(projeto.getId(),cargos.remove(projeto.getId()));
		return true;
	}
	@PostConstruct
	public void criaDados(){
		this.save(new Cargo("Analista Desenvolvedor"));
		this.save(new Cargo("Analista de Testes"));
		this.save(new Cargo("Analista de Banco de Dados"));
		this.save(new Cargo("Gerente de Projetos"));
	}
}
