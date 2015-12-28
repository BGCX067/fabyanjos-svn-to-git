package com.alfa.tracking.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alfa.tracking.exception.ConsultaException;
import com.alfa.tracking.model.Evento;


public class ConsultaService {
	
	@SuppressWarnings("unchecked")
	public Evento buscaSRO(String nroObjeto) throws ConsultaException {
		Evento saida = null;
		try {
			// acessa SRO
			String retHTML = callSRO(nroObjeto);

			if(retHTML != null && !retHTML.trim().equals("")) {

				SAXReader reader = new SAXReader();
				StringReader sr = new StringReader(retHTML);
				Document doc = reader.read(sr);
				List<Node> eventos = (List<Node>) doc.selectNodes("//sroxml/objeto/evento");
				System.out.println(doc.asXML());

				// processa eventos
				for(Node node : eventos) {
					saida = new Evento();
					String dtHr = node.valueOf("data") + " " + node.valueOf("hora");
					saida.setDtHora(dtHr);
					saida.setDescricao(node.valueOf("descricao"));
					saida.setCodigo(node.valueOf("codigo"));
					saida.setLocal(node.valueOf("local"));
					saida.setCidade(node.valueOf("cidade"));
					saida.setUf(node.valueOf("uf"));
					saida.setTipo(node.valueOf("tipo"));
					saida.setStatus(node.valueOf("status"));
				}
			}
		} catch(Exception ex) {
			System.err.println("Erro durante a consulta no SRO: " + ex.getMessage());
			throw new ConsultaException("Erro consulta SRO: " + ex.getMessage());
		}
		return saida;
	}
	
	private String callSRO(String nroObjeto) throws ConsultaException {
		String saida = "";
		
		try {
			URL url = new URL("http://websro.correios.com.br/sro_bin/sroii_xml.eventos");
			
			/*// Configura��o Proxy Inicio
			Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("804058", "angel16".toCharArray());
                }
            });
            System.setProperty("http.proxyHost", "proxy01.prodesp.sp.gov.br");  
            System.setProperty("http.proxyPort", "80");
            // Configura��o Proxy Fim
			 */            
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write("usuario=ECT&senha=SRO&tipo=L&resultado=U&objetos=" + nroObjeto.toUpperCase());
	        // Usuario e senha disponibilizado pelos correios
	        // resultado U ultimo resultado ou T todos os eventos 
	        // objetos, lista para consulta
	        writer.close();
	        
	        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {			
				throw new Exception("Correios - SRO - falha: " + connection.getResponseMessage());
			}
	        DataInputStream inStream = new DataInputStream(connection.getInputStream());
			
	    	String inputLine;
	    	while ((inputLine = inStream.readLine()) != null) {
				saida += inputLine;
			}
	    	inStream.close();

		} catch (Exception e) {
			System.err.println("Erro durante a consulta no SRO: " + e.getMessage());
			throw new ConsultaException("Erro consulta SRO: " + e.getMessage());
		}
		return saida;
	}
	
	public String getHtmlFor(String nroObjeto) throws ConsultaException{
		String resposta = "";
		try {
			URL url = new URL("http://websro.correios.com.br/sro_bin/txect01$.QueryList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setReadTimeout(2000);
	        connection.setRequestMethod("GET");
	        
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write("P_LINGUA=001&P_TIPO=001&P_COD_UNI=" + nroObjeto.toUpperCase());
	        writer.close();
	        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {			
				throw new Exception("Correios - SRO - falha: " + connection.getResponseMessage());
			}
	        BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			
	    	String inputLine;
	    	while ((inputLine = inStream.readLine()) != null) {
				resposta += inputLine;
			}
	    	inStream.close();
	        
		}catch (Exception e) {
			System.err.println("Erro durante a consulta no SRO: " + e.getMessage());
			throw new ConsultaException("Erro consulta SRO: " + e.getMessage());
		}
		
		return resposta;
		
	}
	
	public static void main(String[] args) throws Exception {
		new ConsultaService().buscaSRO("SW238639703BR");
	}
}
