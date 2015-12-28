package br.com.estudo.bean;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.estudo.bean.form.ContatoFormBean;
import br.com.estudo.bean.helper.ContatoHelper;
import br.com.estudo.bean.helper.UsuarioHelper;
import br.com.estudo.delegate.ContatoDelegate;
import br.com.estudo.dto.ContatoDTO;
import br.com.estudo.dto.UsuarioDTO;

public class ContatoMB {

	private ContatoFormBean contato = null;

	public ContatoMB() {
		contato = new ContatoFormBean();
	}

	public String save() {
		try {
			ContatoDTO contato = ContatoHelper.getInstance().createDTO(
					this.contato);
			ContatoDelegate.getInstance().save(contato);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	public String list() {
		try {
			UsuarioDTO user = UsuarioHelper.getInstance().readSessionUser();
			List<ContatoDTO> contatos = ContatoDelegate.getInstance()
					.readByUser(user.getId());
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext exc = fc.getExternalContext();
			exc.getRequestMap().put("contatos", contatos);
		} catch (Exception e) {
			return "error";
		}
		return "listContacts";
	}

	public ContatoFormBean getContato() {
		return contato;
	}

	public void setContato(ContatoFormBean contato) {
		this.contato = contato;
	}
}
