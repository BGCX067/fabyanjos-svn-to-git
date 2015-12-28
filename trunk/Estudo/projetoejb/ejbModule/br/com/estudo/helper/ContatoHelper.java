package br.com.estudo.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.entities.Contato;
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

	public Contato createEntity(ContatoDTO dto) throws HelperException {
		Contato entity = new Contato();
		try {
			BeanUtils.copyProperties(entity, dto);
			if (dto.getUser() != null)
				entity.setUsuario(UsuarioHelper.getInstance().createEntity(
						dto.getUser()));
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return entity;
	}

	public ContatoDTO createDTO(Contato entity) throws HelperException {
		ContatoDTO dto = new ContatoDTO();
		try {
			BeanUtils.copyProperties(dto, entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return dto;
	}
	
	public List<ContatoDTO> createDTO(List<Contato> entities) throws HelperException {
		List<ContatoDTO> contatos = new ArrayList<ContatoDTO>();
		try {
			if(entities != null) {
				for(Contato entity : entities) {
					ContatoDTO dto = new ContatoDTO();
					BeanUtils.copyProperties(dto, entity);
					contatos.add(dto);
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new HelperException(e);
		}
		return contatos;
	}
}
