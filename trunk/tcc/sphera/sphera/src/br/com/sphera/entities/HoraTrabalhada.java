package br.com.sphera.entities;

import java.io.Serializable;
import java.util.Date;

public class HoraTrabalhada implements Serializable {
	private Long id;
	private Double qdHoras;
	private Date dtCadastro;
	private Usuario user;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQdHoras() {
		return qdHoras;
	}

	public void setQdHoras(Double qdHoras) {
		this.qdHoras = qdHoras;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
