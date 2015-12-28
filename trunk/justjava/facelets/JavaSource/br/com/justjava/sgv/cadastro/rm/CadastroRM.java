/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.rm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CadastroRM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nomeRM;

	private String CPF;

	private String RG;

	private String Ramal;

	private String telefoneResidencial;

	private String celular;

	private String email;

	private boolean ativo;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public String getNomeRM() {
		return nomeRM;
	}

	public void setNomeRM(String nomeRM) {
		this.nomeRM = nomeRM;
	}

	public String getRamal() {
		return Ramal;
	}

	public void setRamal(String ramal) {
		Ramal = ramal;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rg) {
		RG = rg;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
