package br.com.estudo.session.interfaces;

import java.util.List;

import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.exception.BussinessException;

public interface ContatoSession {
	
	public static final String JNDI_NAME = "ContatoSessionRemote";
	public static final String LOCAL_JNDI_NAME = "ContatoSessionLocal";
	
	public void save(ContatoDTO contato) throws BussinessException;
	public List<ContatoDTO> readByUser(Integer idUser) throws BussinessException;

}
