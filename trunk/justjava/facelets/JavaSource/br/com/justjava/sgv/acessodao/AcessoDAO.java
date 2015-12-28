/**
 *
 */
package br.com.justjava.sgv.acessodao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.justjava.sgv.acesso.Acesso;

/**
 * @author Eduardo Bregaida
 *
 */
public class AcessoDAO {

	private Session session;

	private Logger logger = Logger.getLogger(Acesso.class);

	public AcessoDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}

	/**
	 * @verifica se o usuário está autorizado para logar no sistema
	 */
	@SuppressWarnings("unchecked")
	public String verificaAutenticacao(Acesso acesso) {
		logger.info("HQL" + acesso.getLogin());

		Query query = session
				.createQuery("select acesso from Acesso as acesso where acesso.login = :login and acesso.senha = :senha");
		query.setParameter("senha", acesso.getSenha());
		query.setParameter("login", acesso.getLogin());

		Acesso acesso2 = (Acesso) query.uniqueResult();

		if (acesso2 == null) {
			return "erro";
		} else {
			if (acesso2.isAdministrador()){
				return "administrador";
			}else
				return "usuario";
		}

	}

	/**
	 * @verifica salva um novo usuário no sistema
	 */
	public void salvaUsuario(Acesso acesso) {
		session.saveOrUpdate(acesso);
	}

	/**
	 * @verifica exclui um usuário no sistema
	 */
	public void excluiUsuario(Acesso acesso) {
		session.delete(acesso);
	}


	@SuppressWarnings("unchecked")

	public Acesso selecionaUsuario(Acesso acesso) {
		// TODO Auto-generated method stub
		return (Acesso) session.load(Acesso.class, acesso.getLogin());
	}



}
