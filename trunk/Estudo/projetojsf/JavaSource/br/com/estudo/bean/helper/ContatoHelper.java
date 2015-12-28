package br.com.estudo.bean.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.estudo.bean.form.ContatoFormBean;
import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.exception.HelperException;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ContatoHelper {

	private static final Logger log = Logger.getLogger(ContatoHelper.class
			.getName());
	private static ContatoHelper instance = null;

	private ContatoHelper() {
	}

	public static ContatoHelper getInstance() {
		if (instance == null)
			instance = new ContatoHelper();
		return instance;
	}

	public ContatoDTO createDTO(ContatoFormBean bean) throws HelperException {
		ContatoDTO dto = new ContatoDTO();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext exc = fc.getExternalContext();
		try {
			BeanUtils.copyProperties(dto, bean);
			dto.setUser((UsuarioDTO) exc.getSessionMap().get("user"));
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return dto;
	}
}
