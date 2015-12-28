/**
 *
 */
package br.com.grecco.dadosdao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.grecco.dados.Dados;
import br.com.justjava.sgv.acesso.Acesso;

/**
 * @author Eduardo Bregaida
 *
 */
public class DadosDAO {

	private Session session;

	private Logger logger = Logger.getLogger(Acesso.class);

	public DadosDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}



	/**
	 * @verifica salva um novo dados no sistema
	 */
	public void salvaDados(Dados dados) {
		session.saveOrUpdate(dados);
	}

	/**
	 * @verifica exclui os dados do sistema
	 */
	public void excluiDados(Dados dados) {
		session.delete(dados);
	}


	@SuppressWarnings("unchecked")
	public Dados selecionaDados(Dados dados) {
		// TODO Auto-generated method stub
		return (Dados) session.load(Dados.class, dados.getId());
	}



}
