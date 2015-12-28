package br.com.sphera.entities.enums;

public enum TarefaStatus {

	AGUARDANDO,
	INPROGRESS,
	CONCLUIDO;
	
	public String toString(){
		return name().toLowerCase();
	}
}
