package com.cashflow.entities;

import java.io.Serializable;
import java.util.Date;

public class Orcamento implements Serializable {

	private Long id;
	private String valor;
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
