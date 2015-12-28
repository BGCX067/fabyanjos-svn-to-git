package br.com.sphera.util;

import java.text.MessageFormat;

public class StringUtils {

	/**
	 * Verifica se a string passada é vazia
	 * @param value String a ser validada
	 * @return booleano indicando se é verdade
	 */
	public static boolean isEmpty(String value){
		return value == null || value.trim().length()==0;
	}

	/**
	 * Insere os parametros em expressões do tipo:
	 * 'bla bla bla {0} {1}'
	 * de acordo com os paratros passados na ordem
	 * @param message mensagem contendo os caracteres especiais
	 * @param args argumentos que serão inseridos na mensagem
	 * @return a String formatada
	 */
	public static String fillArgs(String message, Object... args){
		return MessageFormat.format(message, args);
	}
	
}
