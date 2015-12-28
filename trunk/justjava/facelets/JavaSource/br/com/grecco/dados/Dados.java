/**
 *
 */
package br.com.grecco.dados;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author OTTA956
 *
 */
@Entity
public class Dados {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	private Calendar dataAtual = new GregorianCalendar();

	private String clienteOrigem;

	private String cidadeOrigem;

	private String clienteDestino;

	private String cidadeDestino;

	private Calendar dataSaida = new GregorianCalendar();

	private Calendar horaSaida = new GregorianCalendar();

	private boolean critico;

	private Calendar dataProgramada = new GregorianCalendar();

	private Calendar horaProgramada = new GregorianCalendar();

	private String embarcador;

	private String situacao;

	private boolean atraso;

	private String observacao;

	private String status;

	public boolean isAtraso() {
		return atraso;
	}

	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}

	public String getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public String getClienteDestino() {
		return clienteDestino;
	}

	public void setClienteDestino(String clienteDestino) {
		this.clienteDestino = clienteDestino;
	}

	public String getClienteOrigem() {
		return clienteOrigem;
	}

	public void setClienteOrigem(String clienteOrigem) {
		this.clienteOrigem = clienteOrigem;
	}

	public Calendar getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Calendar dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Calendar getDataProgramada() {
		return dataProgramada;
	}

	public void setDataProgramada(Calendar dataProgramada) {
		this.dataProgramada = dataProgramada;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getEmbarcador() {
		return embarcador;
	}

	public void setEmbarcador(String embarcador) {
		this.embarcador = embarcador;
	}

	public Calendar getHoraProgramada() {
		return horaProgramada;
	}

	public void setHoraProgramada(Calendar horaProgramada) {
		this.horaProgramada = horaProgramada;
	}

	public Calendar getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Calendar horaSaida) {
		this.horaSaida = horaSaida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCritico(boolean critico) {
		this.critico = critico;
	}

	public boolean isCritico() {
		return critico;
	}


}
