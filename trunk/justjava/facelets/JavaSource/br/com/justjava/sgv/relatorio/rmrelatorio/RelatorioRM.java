/**
 *
 */
package br.com.justjava.sgv.relatorio.rmrelatorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Eduardo Bregaida
 *
 */

public class RelatorioRM extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static Connection con;

	public static String banco = "projetosgv"; // Nome do banco de dados

	public static String usuario = "root"; // Usuario do banco

	public static String senha = "root"; // Senha

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		conexao(); // Faz a conex�o
		// Na variavel pathJasper ficara o caminho do diret�rio para
		// os relat�rios compilados (.jasper)
		String pathJasper = getServletContext().getRealPath(
				"/WEB-INF/classes/br/com/justjava/sgv/relatorio/rmrelatorio")
				+ "/";
		// A variavel path armazena o caminho real para o contexto
		// isso � util pois o seu web container pode estar instalado em lugares
		// diferentes
		// String path = getServletContext().getRealPath("/");
		// Parametros do relatorio
		Map parametros = new HashMap();
		//parametros.put("",pathJasper + "CadastroDeRMS.jasper");
		try {
			// Aqui ele cria o relat�rio
			JasperPrint impressao = JasperFillManager.fillReport(pathJasper
					+ "CadastroDeRMS.jasper", parametros, con);
			// Grava o relat�rio em disco em pdf
			JasperManager.printReportToPdfFile(impressao, pathJasper
					+ "/CadastroDeRMS.pdf");
			// Redireciona para o pdf gerado
			res.sendRedirect("CadastroDeRMS.pdf");
		} catch (Exception e) {
			res.getWriter().println("Erro ao gerar o relat�rio: " + e);
		}
	}

	// Cria a conex�o
	public void conexao() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + banco, usuario, senha);
			}
		} catch (Exception e) {
			System.out.println("n�o foi poss�vel conectar ao banco ->");
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		doPost(arg0, arg1);
	}
}
