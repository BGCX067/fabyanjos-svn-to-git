package br.com.estudo.bean.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.estudo.bean.form.UsuarioFormBean;
import br.com.estudo.dto.UsuarioDTO;
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

	public UsuarioDTO createDTO(UsuarioFormBean bean) throws HelperException {
		UsuarioDTO dto = new UsuarioDTO();
		try {
			BeanUtils.copyProperties(dto, bean);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return dto;
	}
	
	public UsuarioDTO readSessionUser() throws HelperException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext exc = fc.getExternalContext();
		
		return (UsuarioDTO) exc.getSessionMap().get("user");
	}
}
