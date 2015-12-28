package br.com.estudo.session.interfaces;

import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.exception.BussinessException;

public interface UsuarioSession {
	
	public static final String JNDI_NAME = "UsuarioSessionRemote";
	public static final String LOCAL_JNDI_NAME = "UsuarioSessionLocal";
	
	public void save(UsuarioDTO usuario) throws BussinessException;
	public UsuarioDTO readByLoginPassword(String login, String password) throws BussinessException;

}
