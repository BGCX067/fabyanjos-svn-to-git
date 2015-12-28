package br.com.sphera.entities;

import br.com.sphera.entities.enums.AtividadeStatus;

public class Atividade extends AbstractTask {
	
	public Tarefa tarefa;
	private AtividadeStatus status;
	
	public Atividade() {
		setAtivo(true);
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public AtividadeStatus getStatus() {
		return status;
	}
	public void setStatus(AtividadeStatus status) {
		this.status = status;
	}
	
}