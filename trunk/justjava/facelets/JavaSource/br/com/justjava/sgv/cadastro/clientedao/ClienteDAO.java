/**
 * @author Eduardo Bregaida
 *
 */
package br.com.justjava.sgv.cadastro.clientedao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import br.com.justjava.sgv.cadastro.cliente.CadastroCliente;

public class ClienteDAO {

	private Session session;

	private Logger logger = Logger.getLogger(CadastroCliente.class);

	public ClienteDAO(Session session) {
		logger.info("Construtor" + session);
		this.session = session;

	}

	/**
	 * @verifica salva novos dados no sistema
	 */
	public void salvaDados(CadastroCliente cadastroCliente) {
		session.saveOrUpdate(cadastroCliente);
	}

	/**
	 * @verifica exclui os dados do sistema
	 */
	public void excluiDados(CadastroCliente cadastroCliente) {
		session.delete(cadastroCliente);
	}

	@SuppressWarnings("unchecked")
	public CadastroCliente selecionaDados(CadastroCliente cadastroCliente) {
		return (CadastroCliente) session.load(CadastroCliente.class,
				cadastroCliente.getId());
	}

}
