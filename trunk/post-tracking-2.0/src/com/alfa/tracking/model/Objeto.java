package com.alfa.tracking.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.alfa.tracking.spring.helper.CustomDateSerializer;

@Entity
public class Objeto implements Serializable {

	private static final long serialVersionUID = 4391557747920517659L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero; 
	private Date createDate;
	@Column(nullable = true)
	private Date statusDate;
	private Integer lastStatusCod;
	private String lastTipo;
	private String lastStatusDesc;
	private Date lastStatusDate;
	private boolean open;
	private String description;

	public Objeto() {
		this.createDate = Calendar.getInstance().getTime();
		this.open = true;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLastStatusDate(Date lastStatusDate) {
		this.lastStatusDate = lastStatusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	

	public void setLastStatusCod(Integer lastStatusCod) {
		this.lastStatusCod = lastStatusCod;
	}

	public void setLastTipo(String lastTipo) {
		this.lastTipo = lastTipo;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public void setLastStatusDesc(String lastStatusDesc) {
		this.lastStatusDesc = lastStatusDesc;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public String getDescription() {
		return description;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getLastStatusDate() {
		return lastStatusDate;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getStatusDate() {
		return statusDate;
	}
	
	public Integer getLastStatusCod() {
		return lastStatusCod;
	}
	
	public String getLastTipo() {
		return lastTipo;
	}
	
	public String getLastStatusDesc() {
		return lastStatusDesc;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public boolean isOpen() {
		return open;
	}
	
	@Transient
	public String getUrl() {
		return "http://websro.correios.com.br/sro_bin/txect01$.QueryList?P_LINGUA=001&P_TIPO=001&P_COD_UNI=";
	}
	
}
