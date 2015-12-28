package br.com.sphera.entities.enums;

public enum AtividadeStatus {

	AGUARDANDO,
	INPROGRESS,
	CONCLUIDO;
	
	public String toString(){
		return name().toLowerCase();
	}
}
