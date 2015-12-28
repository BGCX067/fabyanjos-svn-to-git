/**
 * @author Eduardo Bregaida
 *
 */

package br.com.justjava.sgv.cadastro.visita;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.justjava.sgv.cadastro.campanha.CadastroCampanha;
import br.com.justjava.sgv.cadastro.cliente.CadastroCliente;
import br.com.justjava.sgv.cadastro.rm.CadastroRM;

@Entity
public class CadastroVisita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descricao;

	@Temporal(TemporalType.DATE)
	private Calendar data = new GregorianCalendar();

	@ManyToOne
	private CadastroRM cadastroRM;

	@ManyToOne
	private CadastroCliente cadastroCliente;

	@ManyToOne
	private CadastroCampanha cadastroCampanha;

	public CadastroCampanha getCadastroCampanha() {
		return cadastroCampanha;
	}

	public void setCadastroCampanha(CadastroCampanha cadastroCampanha) {
		this.cadastroCampanha = cadastroCampanha;
	}

	public CadastroCliente getCadastroCliente() {
		return cadastroCliente;
	}

	public void setCadastroCliente(CadastroCliente cadastroCliente) {
		this.cadastroCliente = cadastroCliente;
	}

	public CadastroRM getCadastroRM() {
		return cadastroRM;
	}

	public void setCadastroRM(CadastroRM cadastroRM) {
		this.cadastroRM = cadastroRM;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
