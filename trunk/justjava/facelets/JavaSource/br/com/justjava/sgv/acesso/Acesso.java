/**
 * Login e acesso ao sistema
 */
package br.com.justjava.sgv.acesso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;

import br.com.justjava.sgv.acessodao.AcessoDAO;
import br.com.justjava.sgv.hibernate.hibernateutil.HibernateUtil;

/**
 * @author Eduardo Bregaida
 *
 */

@Entity
public class Acesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	private String login;

	private String senha;

	private boolean administrador;

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String logar() {
		Session session = HibernateUtil.openSession();
		AcessoDAO acessoDAO = new AcessoDAO(session);
		return acessoDAO.verificaAutenticacao(this);
	}

	public void salvar() {
		Session session = HibernateUtil.openSession();
		AcessoDAO acessoDAO = new AcessoDAO(session);
		acessoDAO.salvaUsuario(this);
	}

	public void deletar() {
		Session session = HibernateUtil.openSession();
		AcessoDAO acessoDAO = new AcessoDAO(session);
		acessoDAO.excluiUsuario(this);
	}


}
