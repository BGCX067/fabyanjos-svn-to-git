/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.rmdao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.rm.CadastroRM;

public class RMDAO {

	private Session session;

	private Logger logger = Logger.getLogger(CadastroRM.class);

	public RMDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}

	/**
	 * @verifica salva novos dados no sistema
	 */
	public void salvaDados(CadastroRM cadastroRM) {
		session.saveOrUpdate(cadastroRM);
	}

	/**
	 * @verifica exclui os dados do sistema
	 */
	public void excluiDados(CadastroRM cadastroRM) {
		session.delete(cadastroRM);
	}

	@SuppressWarnings("unchecked")
	public CadastroRM selecionaDados(CadastroRM cadastroRM) {
		return (CadastroRM) session.load(CadastroRM.class, cadastroRM.getId());
	}

}
