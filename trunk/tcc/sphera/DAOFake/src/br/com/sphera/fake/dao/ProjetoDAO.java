package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;
import br.com.sphera.entities.Tarefa;

public class ProjetoDAO {

	private HashMap<Long, Projeto> projetos;
	private HashMap<Long, Projeto> lixo;
	private Long id = 1L;
	private UsuarioDAO daoUsuario;
	private ClienteDAO daoCliente;
	private FaseDao daoFase;
	private TarefaDAO daoTarefa;
	
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
	public void setDaoTarefa(TarefaDAO daoTarefa) {
		this.daoTarefa = daoTarefa;
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
		cal.set(2007, Calendar.OCTOBER, 28);
		Projeto p = new Projeto();
		p.setNome("Trabalho de Conclusão de Curso");
		p.setCliente(daoCliente.load(1L));
		p.setResponsavel(daoUsuario.load(1L));
		StringBuffer sb = new StringBuffer();
		sb.append("Objetivo:")
		.append("\nConstruir uma ferramenta que facilite o gerenciamento das ")
		.append("etapas no desenvolvimento de qualquer tipo de projeto, ")
		.append("buscando organizar e conduzir de maneira prática e eficiente ")
		.append("todo o trabalho.")
		.append("\nJustificativa:")
		.append("\n - Aprofundar-se na área de gestão de projetos;")
		.append("\n - 30% das empresas utilizam soluções próprias;")
		.append("\n - Grande interesse das empresas por metodologias de gestão de projetos;")
		.append("\n - Ferramentas pouco amigáveis e/ou flexíveis");
		p.setDescricao(sb.toString());
		p.setCustoPlanejadoMaterial(50.0);
		p.setCustoPlanejadoRH(3000.0);
		p.setDataCriacao(new Date(cal.getTimeInMillis()));
		
		p.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.NOVEMBER, 15);
		
		p.setFimPlanejado(new Date(cal.getTimeInMillis()));
		save(p);
		
		//projeto 2
		/*
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
		*/
		criaFases();
		criaTarefas();
		
	}

	public void criaFases(){
		List<Fase> fases = new ArrayList<Fase>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2007, Calendar.OCTOBER, 28);
		//fase 1
		Fase fase = new Fase();
		fase.setNome("TCC-1");
		fase.setDescricao("Estudo de Viabilidade, Documentação do TCC, Engenharia do Sistema");
		fase.setCustoPlanejadoMaterial(30.0);
		fase.setCustoPlanejadoRH(1500.0);
		fase.setDataCriacao(new Date(cal.getTimeInMillis()));
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.JUNE, 27);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(1L));
		daoFase.save(fase);
		fases.add(fase);
		
		//fase 2
		fase = new Fase();
		fase.setNome("TCC-2");
		fase.setDescricao("Implementação do Sistema");
		fase.setCustoPlanejadoMaterial(20.0);
		fase.setCustoPlanejadoRH(1500.0);
		fase.setDataCriacao(new Date());
		cal.set(2008, Calendar.JUNE, 20);
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.NOVEMBER, 15);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(1L));
		fases.add(fase);
		daoFase.save(fase);
		load(1L).setFases(fases);
		
		/*
		fases = new ArrayList<Fase>();
		
		daoFase.save(fase);
		//fase 3
		fase = new Fase();
		fase.setNome("Iteração única");
		fase.setDescricao("Iteração Única");
		fase.setCustoPlanejadoMaterial(2000.0);
		fase.setCustoPlanejadoRH(3000.0);
		fase.setDataCriacao(new Date());
		cal.set(2008, 8, 20);
		fase.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2009, 2, 20);
		fase.setFimPlanejado(new Date(cal.getTimeInMillis()));
		fase.setProjeto(load(2L));
		daoFase.save(fase);	
		fases.add(fase);
		load(2L).setFases(fases);
		*/
	}
	public void criaTarefas(){
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2007, Calendar.OCTOBER, 28);
		
		Tarefa tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(50.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Estudo de Viabilidade");
		tarefa.setNome("Estudo de Viabilidade");
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2007, Calendar.NOVEMBER, 20);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Análise do PMBoK");
		tarefa.setNome("Análise do PMBoK");
		cal.set(2007, Calendar.OCTOBER, 28);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2007, Calendar.NOVEMBER, 20);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(50.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Estudo de Mercado");
		tarefa.setNome("Estudo de Mercado");
		cal.set(2007, Calendar.DECEMBER, 05);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.FEBRUARY, 05);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(450.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Desenvolvimento do Documento");
		tarefa.setNome("Desenvolvimento do Documento");
		cal.set(2007, Calendar.NOVEMBER, 15);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.MAY, 28);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(60.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Engenharia do Sistema");
		tarefa.setNome("Engenharia do Sistema");
		cal.set(2008, Calendar.MARCH, 15);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.APRIL, 28);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(30.0);
		tarefa.setDescricao("Correções do Documento");
		tarefa.setNome("Correções do Documento");
		cal.set(2008, Calendar.JANUARY, 28);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.MAY, 30);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Apresentação TCC1");
		tarefa.setNome("Apresentação TCC1");
		cal.set(2008, Calendar.JUNE, 19);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.JUNE, 27);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Criação de Protótipo");
		tarefa.setNome("Protótipo");
		cal.set(2008, Calendar.MAY, 10);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.JUNE, 19);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(1L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		daoFase.load(1L).setTarefas(tarefas);
		//fase 2
		tarefas = new ArrayList<Tarefa>();
		
		
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Criação do Diagram do Banco, Criação do script, Otimizações, Criação de Dados para desenvolvimento");
		tarefa.setNome("Criação do Banco de Dados");
		cal.set(2008, Calendar.JUNE, 20);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.AUGUST, 25);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(650.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Dados Persistidos, regras de negócio implementadas, casos de uso implementados");
		tarefa.setNome("Implementação do Sistema");
		cal.set(2008, Calendar.JUNE, 20);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.SEPTEMBER, 15);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Testes com base nas Regras de Negócio, validação dos casos de uso.");
		tarefa.setNome("Testes no sistema");
		cal.set(2008, Calendar.SEPTEMBER, 15);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.OCTOBER, 15);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Correção dos itens encontrados nos Testes.");
		tarefa.setNome("Correções");
		cal.set(2008, Calendar.SEPTEMBER, 16);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.OCTOBER, 15);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(30.0);
		tarefa.setCustoPlanejadoMaterial(20.0);
		tarefa.setDescricao("Revisão da Monografia");
		tarefa.setNome("Revisão da Monografia.");
		cal.set(2008, Calendar.OCTOBER, 16);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.NOVEMBER, 31);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(200.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Apresentação Final");
		tarefa.setNome("Apresentação Final.");
		cal.set(2008, Calendar.NOVEMBER, 1);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, Calendar.NOVEMBER, 15);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(2L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		daoFase.load(2L).setTarefas(tarefas);
		
		
		/*
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(1000.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Analise");
		tarefa.setNome("Analise");
		cal.set(2008, 8, 20);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, 9, 10);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(3L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(1000.0);
		tarefa.setCustoPlanejadoMaterial(3000.0);
		tarefa.setDescricao("Desenvolvimeto");
		tarefa.setNome("Desenvolvimento");
		cal.set(2008, 9, 10);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2008, 13, 20);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(3L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(1000.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Testes");
		tarefa.setNome("Testes");
		cal.set(2008, 13, 20);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2009, 1, 20);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(3L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		tarefa.setCustoPlanejadoRH(1000.0);
		tarefa.setCustoPlanejadoMaterial(0.0);
		tarefa.setDescricao("Revisão");
		tarefa.setNome("Revisão");
		cal.set(2008, 1, 20);
		tarefa.setInicioPlanejado(new Date(cal.getTimeInMillis()));
		cal.set(2009, 2, 20);
		tarefa.setFimPlanejado(new Date(cal.getTimeInMillis()));
		tarefa.setDataCriacao(new Date());
		tarefa.setFase(daoFase.load(3L));
		daoTarefa.save(tarefa);
		tarefas.add(tarefa);
		daoFase.load(3L).setTarefas(tarefas);
		*/
	}

}
