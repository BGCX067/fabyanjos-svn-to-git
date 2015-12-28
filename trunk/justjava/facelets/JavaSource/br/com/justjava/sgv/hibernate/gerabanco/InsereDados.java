package br.com.justjava.sgv.hibernate.gerabanco;

import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.justjava.sgv.acesso.Acesso;
import br.com.justjava.sgv.cadastro.cliente.CadastroCliente;
import br.com.justjava.sgv.cadastro.rm.CadastroRM;
import br.com.justjava.sgv.cadastro.visita.CadastroVisita;

public class InsereDados {
	public static void main(String[] args) {
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure();
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);

		// Fabrica de Sessão
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		Acesso acesso = new Acesso();// ainda é transient
		acesso.setLogin("Eduardo");
		acesso.setSenha("123");
		acesso.setAdministrador(false);
		session.saveOrUpdate(acesso);

		/*CadastroCliente cadastroCliente = new CadastroCliente();
		cadastroCliente.setCnpj("349.152.521.98");
		cadastroCliente.setEmail("eduardo.bregaida@tcs.com");
		cadastroCliente.setEndereco("Rua bla bla bla bla");
		cadastroCliente.setNomeCliente("XPTO");
		cadastroCliente.setTelefone("3737-7380");
		session.saveOrUpdate(cadastroCliente);

		CadastroCliente cadastroCliente2 = new CadastroCliente();
		cadastroCliente2.setCnpj("349.152.521.9222228");
		cadastroCliente2.setEmail("eduardo.bregaida@tcs.cdsvsdvom");
		cadastroCliente2.setEndereco("Rua bla bla dvdsdsvbla bla");
		cadastroCliente2.setNomeCliente("XPTOsssss");
		cadastroCliente2.setTelefone("3737-7322");
		session.saveOrUpdate(cadastroCliente2);

		CadastroRM cadastroRM = new CadastroRM();
		cadastroRM.setCelular("9999-8587");
		cadastroRM.setCPF("349.845.965-85");
		cadastroRM.setEmail("eduardo.bregaida@gmail.com");
		cadastroRM.setNomeRM("Eduardo Bregaida");
		cadastroRM.setRamal("277");
		cadastroRM.setRG("40.521.844-9");
		cadastroRM.setTelefoneResidencial("4226-8549");
		session.saveOrUpdate(cadastroRM);

		CadastroRM cadastroRM2 = new CadastroRM();
		cadastroRM2.setCelular("9999");
		cadastroRM2.setCPF("349.845");
		cadastroRM2.setEmail("bregaida@gmail.com");
		cadastroRM2.setNomeRM("Bregaida2");
		cadastroRM2.setRamal("jkj");
		cadastroRM2.setRG("40.844-9");
		cadastroRM2.setTelefoneResidencial("8549");
		session.saveOrUpdate(cadastroRM2);

		CadastroCliente cadastroCliente = new CadastroCliente();
		cadastroCliente.setNomeCampanha("Plus");
		cadastroCliente.setObservacaoCampanha("Campanha para ajuda social");
		session.saveOrUpdate(cadastroCliente);

		CadastroVisita cadastroVisita = new CadastroVisita();
		cadastroVisita.setData(Calendar.getInstance());
		cadastroVisita.setDescricao("Visita de Neg�cios");
		cadastroVisita.setCadastroRM(cadastroRM);
		session.saveOrUpdate(cadastroVisita);
*/

		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
