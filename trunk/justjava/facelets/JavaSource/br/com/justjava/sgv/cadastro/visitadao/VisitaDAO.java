/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.visitadao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.visita.CadastroVisita;

public class VisitaDAO {

	private Session session;

	private Logger logger = Logger.getLogger(CadastroVisita.class);

	public VisitaDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}

	/**
	 * @verifica salva novos dados no sistema
	 */
	public void salvaDados(CadastroVisita cadastroVisita) {
		session.saveOrUpdate(cadastroVisita);
	}

	/**
	 * @verifica exclui os dados do sistema
	 */
	public void excluiDados(CadastroVisita cadastroVisita) {
		session.delete(cadastroVisita);
	}

	@SuppressWarnings("unchecked")
	public CadastroVisita selecionaDados(CadastroVisita cadastroVisita) {
		return (CadastroVisita) session.load(CadastroVisita.class,
				cadastroVisita.getId());
	}

}
