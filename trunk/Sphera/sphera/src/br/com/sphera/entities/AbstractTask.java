package br.com.sphera.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class AbstractTask implements Serializable{
	
	private Long id;
	private String nome;
	private Date dataCriacao;
	private boolean ativo;
	private Date inicioPlanejado;
	private Date fimPlanejado;
	private Date inicio;
	private Date termino;
	private Double custoPlanejadoRH;
	private Double custoRH;
	private Double custoPlanejadoMaterial;
	private Double custoMaterial;
	private List<HoraTrabalhada> trabalhoRealizado;
	private List<Material> materialUtilizado;
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Date getInicioPlanejado() {
		return inicioPlanejado;
	}
	public void setInicioPlanejado(Date inicioPlanejado) {
		this.inicioPlanejado = inicioPlanejado;
	}
	public Date getFimPlanejado() {
		return fimPlanejado;
	}
	public void setFimPlanejado(Date fimPlanejado) {
		this.fimPlanejado = fimPlanejado;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getTermino() {
		return termino;
	}
	public void setTermino(Date termino) {
		this.termino = termino;
	}
	public Double getCustoPlanejadoRH() {
		return custoPlanejadoRH;
	}
	public void setCustoPlanejadoRH(Double custoPlanejadoRH) {
		this.custoPlanejadoRH = custoPlanejadoRH;
	}
	public Double getCustoRH() {
		return custoRH;
	}
	public void setCustoRH(Double custoRH) {
		this.custoRH = custoRH;
	}
	public Double getCustoPlanejadoMaterial() {
		return custoPlanejadoMaterial;
	}
	public void setCustoPlanejadoMaterial(Double custoPlanejadoMaterial) {
		this.custoPlanejadoMaterial = custoPlanejadoMaterial;
	}
	public Double getCustoMaterial() {
		return custoMaterial;
	}
	public void setCustoMaterial(Double custoMaterial) {
		this.custoMaterial = custoMaterial;
	}
	public List<HoraTrabalhada> getTrabalhoRealizado() {
		return trabalhoRealizado;
	}
	public void setTrabalhoRealizado(List<HoraTrabalhada> trabalhoRealizado) {
		this.trabalhoRealizado = trabalhoRealizado;
	}
	public List<Material> getMaterialUtilizado() {
		return materialUtilizado;
	}
	public void setMaterialUtilizado(List<Material> materialUtilizado) {
		this.materialUtilizado = materialUtilizado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
