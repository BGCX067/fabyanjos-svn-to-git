package br.com.sphera.entities.enums;

public enum FaseStatus {

	AGUARDANDO,
	INPROGRESS,
	CONCLUIDO;
	
	public String toString(){
		return name().toLowerCase();
	}
}
