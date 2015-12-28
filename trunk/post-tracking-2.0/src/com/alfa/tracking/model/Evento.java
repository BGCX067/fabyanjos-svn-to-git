package com.alfa.tracking.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class Evento implements Serializable {

	private static final long serialVersionUID = -5237958647519908279L;
	
	private Key id;
	private String tipo;
	private String status;
	private Date dtHora;
	private String descricao;
	private String local;
	private String codigo;
	private String cidade;
	private String uf;
	private String sto;

	public Date getDtHora() {
		return dtHora;
	}

	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;
	}

	public void setDtHora(String dtHora) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.dtHora = format.parse(dtHora);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String value) {
		this.tipo = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String value) {
		this.descricao = value;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String value) {
		this.local = value;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String value) {
		this.codigo = value;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String value) {
		this.cidade = value;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String value) {
		this.uf = value;
	}

	public String getSto() {
		return sto;
	}

	public void setSto(String value) {
		this.sto = value;
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return tipo + " " + status + " " + dtHora + " " + descricao + " "
				+ local + " " + codigo + " " + cidade + " " + uf + " " + sto;
	}
}
