package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;

public class FaseDao {

	private HashMap<Long, Fase> fases;
	private HashMap<Long, Fase> lixo;
	private Long id = 1L;
	

	public FaseDao() {
		fases = new HashMap<Long, Fase>();
		lixo = new HashMap<Long, Fase>();
	}

	
	public Long save(Fase fase) {
		if (fase.getId() == null || fase.getId() == 0) {
			fase.setId(id++);
		}
		fases.put(fase.getId(), fase);
		return fase.getId();
	}

	public Fase load(Long key) {
		return fases.get(key);
	}

	public List<Fase> listAll() {
		List<Fase> list = new ArrayList<Fase>(fases.values());
		return list;
	}

	public boolean delete(Fase fase) {
		lixo.put(fase.getId(), fases.remove(fase.getId()));
		return true;
	}
	public List<Fase> findFaseNameStartWith(String begin){
		List<Fase> result = new ArrayList<Fase>();
		List<Fase> list = listAll();
		for (Fase fase : list) {
			if(fase.getNome().startsWith(begin)){
				result.add(fase);
			}
		}
		return result;
	}
	
	public List<Fase> findFasesFromProj(Projeto projeto){
		List<Fase> found = new ArrayList<Fase>();
		List<Fase> list = listAll();
		for (Fase fase : list) {
			
			if(fase.getProjeto().equals(projeto)){
				found.add(fase);
			}
		}
		return found;
	}
	public Fase findFaseNameAndProjeName(String faseNome, String projetoNome){
		List<Fase> list = listAll();
		for (Fase fase : list) {
			System.out.println(fase.getNome()+ " = " +faseNome);
			System.out.println(fase.getProjeto().getNome() +" = "+projetoNome);
			if(fase.getNome().equals(faseNome) && fase.getProjeto().getNome().equals(projetoNome)){
				return fase;
			}
		}
		return null;
	}
	
}
