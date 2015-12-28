package br.com.estudo.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1655042684414518176L;

	private Integer id;
	private String nome;
	private String email;
	private String password;
	private List<ContatoDTO> contatos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}
}
