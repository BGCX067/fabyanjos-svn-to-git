package br.com.estudo.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.entities.Usuario;
import br.com.estudo.exception.HelperException;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class UsuarioHelper {

	private static final Logger log = Logger.getLogger(UsuarioHelper.class
			.getName());
	private static UsuarioHelper instance = null;

	private UsuarioHelper() {
	}

	public static UsuarioHelper getInstance() {
		if (instance == null)
			instance = new UsuarioHelper();
		return instance;
	}

	public Usuario createEntity(UsuarioDTO dto) throws HelperException {
		Usuario entity = new Usuario();
		try {
			BeanUtils.copyProperties(entity, dto);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return entity;
	}
	
	public UsuarioDTO createDTO(Usuario entity) throws HelperException {
		UsuarioDTO dto = new UsuarioDTO();
		try {
			BeanUtils.copyProperties(dto, entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return dto;
	}
}
