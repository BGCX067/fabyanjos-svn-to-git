package br.com.sphera.entities;

import java.io.Serializable;
import java.util.Date;

import br.com.sphera.entities.enums.ProjetoStatus;

public class Projeto implements Serializable {
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
	private Usuario responsavel;
	private Cliente cliente;
	private ProjetoStatus status;
	private String descricao;

	public Projeto() {
		setAtivo(true);
		setStatus(ProjetoStatus.AGUARDANDO);
	}
	
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

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ProjetoStatus getStatus() {
		return status;
	}

	public void setStatus(ProjetoStatus status) {
		this.status = status;
	}
	
	/**
	 * @return o custo total do projeto
	 */
	public Double getCustoTotal() {
		Double mat = getCustoMaterial() == null ? 0 : getCustoMaterial();
		Double rh = getCustoRH() == null ? 0 :getCustoRH();
		return mat+rh;
	}
	/**
	 * @return o custo Planejado total do projeto
	 */
	public Double getCustoPlanejadoTotal(){
		Double panMat = getCustoPlanejadoMaterial() == null ? 0 : getCustoPlanejadoMaterial();
		Double panRH = getCustoPlanejadoRH() == null ? 0 :getCustoPlanejadoRH();
		return panMat+panRH;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return nome +" ("+cliente.getNome() + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
