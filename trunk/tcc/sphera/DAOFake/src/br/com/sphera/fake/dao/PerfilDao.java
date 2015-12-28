package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Acesso;
import br.com.sphera.entities.Perfil;

public class PerfilDao {

	private HashMap<Long, Perfil> perfis;
	private HashMap<Long, Perfil> lixo;
	private Long id = 1L;

	public PerfilDao() {
		perfis = new HashMap<Long, Perfil>();
		lixo = new HashMap<Long, Perfil>();
	}

	public Long save(Perfil perfil) {
		if (perfil.getId() == null || perfil.getId() == 0) {
			perfil.setId(id++);
		}
		perfis.put(perfil.getId(), perfil);
		return perfil.getId();
	}

	public Perfil load(Long key) {
		return perfis.get(key);
	}

	public List<Perfil> listAll() {
		List<Perfil> list = new ArrayList<Perfil>(perfis.values());
		return list;
	}

	public boolean delete(Perfil perfil) {
		lixo.put(perfil.getId(), perfis.remove(perfil.getId()));
		return true;
	}
	@PostConstruct
	public void criaDados(){
		for(int i =1; i < 5; i++){
			Perfil perfil = new  Perfil();
			List<Acesso> acessos = new ArrayList<Acesso>();
			Acesso e = new Acesso();
			e.setNome("Acesso "+i);
			e.setId(1L);
			acessos.add(e);
			perfil.setAcessos(acessos);
			perfil.setNome("Perfil "+i);
			perfil.setAtivo(true);
			save(perfil);
		}
	}
}
