/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.campanhadao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.campanha.CadastroCampanha;

public class CampanhaDAO {

	private Session session;

	private Logger logger = Logger.getLogger(CadastroCampanha.class);

	public CampanhaDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}

	/**
	 * @verifica salva novos dados no sistema
	 */
	public void salvaDados(CadastroCampanha cadastroVisita) {
		session.saveOrUpdate(cadastroVisita);
	}

	/**
	 * @verifica exclui os dados do sistema
	 */
	public void excluiDados(CadastroCampanha cadastroVisita) {
		session.delete(cadastroVisita);
	}

	@SuppressWarnings("unchecked")
	public CadastroCampanha selecionaDados(CadastroCampanha cadastroVisita) {
		return (CadastroCampanha) session.load(CadastroCampanha.class,
				cadastroVisita.getId());
	}

}
