package br.com.sphera.entities.enums;

public enum ProjetoStatus {

	AGUARDANDO,
	INPROGRESS,
	CONCLUIDO;
	
	public String toString(){
		return name().toLowerCase();
	}
}
