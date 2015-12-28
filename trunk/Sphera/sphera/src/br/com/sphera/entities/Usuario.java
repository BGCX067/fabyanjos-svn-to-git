package br.com.sphera.entities;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
	private Long id;
	private String nome;
	private boolean ativo;
	private String login;
	private String senha;
	private String email;
	private Double custoHora;
	private String telefone;
	private List<Tarefa> tarefas;
	private Departamento departamento;
	private Cargo cargo;
	private List<Perfil> perfis;

	public Usuario() {
		setAtivo(true);
	}
	public Usuario(String nome, String login, String senha, String email, Double custoHora) {
		this();
		setNome(nome);
		setLogin(login);
		setSenha(senha);
		setEmail(email);
		setCustoHora(custoHora);
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getCustoHora() {
		return custoHora;
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	@Override
	public String toString() {
		return nome+" ("+login+")";
	}
}
