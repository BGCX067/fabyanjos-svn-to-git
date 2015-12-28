package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;

public class ProjetoDAO {

	private HashMap<Long, Projeto> projetos;
	private HashMap<Long, Projeto> lixo;
	private Long id = 1L;
	private UsuarioDAO daoUsuario;
	private ClienteDAO daoCliente;
	private FaseDao daoFase;
	
	public ProjetoDAO() {
		projetos = new HashMap<Long, Projeto>();
		lixo = new HashMap<Long, Projeto>();
	}
	
	public Long save(Projeto projeto) {
		if(projeto.getId() == null || projeto.getId() == 0){
			projeto.setId(id++);
		}
		projetos.put(projeto.getId(), projeto);
		return projeto.getId();
	}
	public Projeto load(Long key) {
		return projetos.get(key);
	}
	public List<Projeto> listAll(){
		List<Projeto> list = new ArrayList<Projeto>(projetos.values());
		return list;
	}
	public boolean delete(Projeto projeto){
		lixo.put(projeto.getId(),projetos.remove(projeto.getId()));
		return true;
	}
	public void setDaoCliente(ClienteDAO daoCliente) {
		this.daoCliente = daoCliente;
	}
	public void setDaoUsuario(UsuarioDAO daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	public void setDaoFase(FaseDao daoFase) {
		this.daoFase = daoFase;
	}
	public List<Projeto> findProjetoNameStartWith(String begin){
		List<Projeto> result = new ArrayList<Projeto>();
		List<Projeto> list = listAll();
		for (Projeto proj : list) {
			if(proj.getNome().startsWith(begin)){
				result.add(proj);
			}
		}
		return result;
	}
	
	public Projeto findProjetoByNameAndNameCliente(String nomeProjeto, String clienteNome){
		List<Projeto> list = this.listAll();
		for (Projeto projeto : list) {
			if(projeto.getNome().equals(nomeProjeto) && projeto.getCliente().getNome().equals(clienteNome)){
				return projeto;
			}
		}
		return null;
	}
	
	@PostConstruct
	public void criaDados(){
		Calendar cal = Calendar.getInstance();
		cal.set(2008, 7, 28);
		Projeto p = new Projeto();
		p.setNome("Projeto X");
		p.setCliente(daoCliente.load(1L));
		p.setResponsavel(daoUsuario.load(1L));
		StringBuffer sb = new StringBuffer();
		sb.append("O projeto Projeto X consiste de auxiliar o cliente Cliente X na criação de um sistema de BI.")
		.append("\nPara isso será avaliado:")
		.append("\n-Os bancos de dados existentes no cliente\n-Mapeado os negócios e os sistemas")
		.append("\n-Implementado o BI no setor mais critico");
		p.setDescricao(sb.toString());
		p.setCustoPlanejadoMaterial(2000.0);
		p.setCustoPlanejadoRH(3000.0);
		p.setDataCriacao(new Date());
		
		p.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, 13, 28);
		p.setFimPlanejado(new Date(cal.getTimeInMillis()));
		save(p);
		
		//projeto 2
		
		p = new Projeto();
		p.setNome("Projeto Astral Alto");
		p.setCliente(daoCliente.load(2L));
		p.setResponsavel(daoUsuario.load(3L));
		sb = new StringBuffer();
		sb.append("O projeto Projeto Astral Alto consiste de auxiliar o cliente Cliente 2 na criação de um sistema Web.")
		.append("\nPara isso será desenvolvido:")
		.append("\n-Arquitetura do sistema")
		.append("\n-Desenvolvimento");
		p.setDescricao(sb.toString());
		p.setCustoPlanejadoMaterial(2000.0);
		p.setCustoPlanejadoRH(3000.0);
		p.setDataCriacao(new Date());
		cal.set(2008, 8, 20);
		p.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2009, 2, 20);
		p.setFimPlanejado(new Date(cal.getTimeInMillis()));
		save(p);
		criaFases();
	}

	public void criaFases(){
		Calendar cal = Calendar.getInstance();
		cal.set(2008, 7, 28);
		//fase 1
		Fase fase = new Fase();
		fase.setNome("Estudo da base de dados do cliente");
		fase.setDescricao("Iteração que acompanha e estuda todas as bases de dados e Sistemas do cliente");
		fase.setCustoPlanejadoMaterial(0.0);
		fase.setCustoPlanejadoRH(1000.0);
		fase.setDataCriacao(new Date());
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, 9, 28);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(1L));
		
		daoFase.save(fase);
		//fase 2
		fase = new Fase();
		fase.setNome("Implantação do Cognos");
		fase.setDescricao("Implantação de BI nos Setores Críticos");
		fase.setCustoPlanejadoMaterial(3000.0);
		fase.setCustoPlanejadoRH(2000.0);
		fase.setDataCriacao(new Date());
		cal.set(2008, 9, 20);
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, 13, 28);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(1L));
		
		daoFase.save(fase);
		//fase 3
		fase = new Fase();
		fase.setNome("Iteração única");
		fase.setDescricao("Iteração Única");
		fase.setCustoPlanejadoMaterial(3000.0);
		fase.setCustoPlanejadoRH(2000.0);
		fase.setDataCriacao(new Date());
		cal.set(2008, 8, 20);
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2009, 2, 20);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(2L));
		daoFase.save(fase);
		
	}

}
