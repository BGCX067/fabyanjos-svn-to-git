package br.com.sphera.entities;

import java.util.List;

import br.com.sphera.entities.enums.TarefaStatus;

public class Tarefa extends AbstractTask{
	
	private Fase fase;
	private TarefaStatus status;
	private List<Tarefa> dependencias;
	private List<Atividade> atividades;
	private Usuario executor;
	
	public Tarefa() {
		setAtivo(true);
	}
	
	public TarefaStatus getStatus() {
		return status;
	}
	public void setStatus(TarefaStatus status) {
		this.status = status;
	}
	public List<Tarefa> getDependencias() {
		return dependencias;
	}
	public void setDependencias(List<Tarefa> dependencias) {
		this.dependencias = dependencias;
	}
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public Usuario getExecutor() {
		return executor;
	}
	public void setExecutor(Usuario executor) {
		this.executor = executor;
	}
	public void setFase(Fase fase) {
		this.fase = fase;
	}
	public Fase getFase() {
		return fase;
	}
}
