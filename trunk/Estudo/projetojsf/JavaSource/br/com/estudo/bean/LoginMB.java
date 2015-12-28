package br.com.estudo.bean;

import br.com.estudo.bean.form.UsuarioFormBean;
import br.com.estudo.bean.helper.LoginHelper;
import br.com.estudo.delegate.UsuarioDelegate;
import br.com.estudo.dto.UsuarioDTO;

public class LoginMB {

	private UsuarioFormBean usuario = null;

	public LoginMB() {
		usuario = new UsuarioFormBean();
	}

	public String login() {
		try {
			UsuarioDTO usuario = UsuarioDelegate.getInstance().login(
					this.usuario.getEmail(), this.usuario.getPassword());
			boolean canLogin = LoginHelper.getInstance().canlogin(usuario);
			if (canLogin)
				return "login";
			return "failure";
		} catch (Exception e) {
			return "error";
		}
	}

	public UsuarioFormBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioFormBean usuario) {
		this.usuario = usuario;
	}
}
