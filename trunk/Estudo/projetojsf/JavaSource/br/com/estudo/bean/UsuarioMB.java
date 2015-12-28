package br.com.estudo.bean;

import br.com.estudo.bean.form.UsuarioFormBean;
import br.com.estudo.bean.helper.UsuarioHelper;
import br.com.estudo.delegate.UsuarioDelegate;
import br.com.estudo.dto.UsuarioDTO;

public class UsuarioMB {

	private UsuarioFormBean usuario = null;
	
	public UsuarioMB() {
		usuario = new UsuarioFormBean();		
	}
	
	public String save() {
		try {
			UsuarioDTO usuario = UsuarioHelper.getInstance().createDTO(this.usuario);
			UsuarioDelegate.getInstance().save(usuario);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}

	public UsuarioFormBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioFormBean usuario) {
		this.usuario = usuario;
	}
}
