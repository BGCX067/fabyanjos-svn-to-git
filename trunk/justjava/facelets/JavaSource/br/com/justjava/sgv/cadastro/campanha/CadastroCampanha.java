/**
 *
 */
package br.com.justjava.sgv.cadastro.campanha;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Eduardo Bregaida
 *
 */

@Entity
public class CadastroCampanha {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nomeCampanha;

	private String observacaoCampanha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public String getObservacaoCampanha() {
		return observacaoCampanha;
	}

	public void setObservacaoCampanha(String observacaoCampanha) {
		this.observacaoCampanha = observacaoCampanha;
	}
}
