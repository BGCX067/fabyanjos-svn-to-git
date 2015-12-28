package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sphera.entities.Acesso;

public class AcessoDao {

	private HashMap<Long, Acesso> acessos;
	private HashMap<Long, Acesso> lixo;
	private Long id = 1L;

	public AcessoDao() {
		acessos = new HashMap<Long, Acesso>();
		lixo = new HashMap<Long, Acesso>();
		for (int i = 0; i < 10; i++) {
			Acesso acesso = new Acesso();
			acesso.setId(Long.valueOf(i+1));
			acesso.setNome("Acesso " + (i+1));
			acessos.put(acesso.getId(), acesso);
		}
	}

	public Long save(Acesso acesso) {
		if (acesso.getId() == null || acesso.getId() == 0) {
			acesso.setId(id++);
		}
		acessos.put(acesso.getId(), acesso);
		return acesso.getId();
	}

	public Acesso load(Long key) {
		return acessos.get(key);
	}

	public List<Acesso> listAll() {
		List<Acesso> list = new ArrayList<Acesso>(acessos.values());
		return list;
	}

	public boolean delete(Acesso fase) {
		lixo.put(fase.getId(), acessos.remove(fase.getId()));
		return true;
	}
}
