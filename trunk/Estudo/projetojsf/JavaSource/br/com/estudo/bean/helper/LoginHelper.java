package br.com.estudo.bean.helper;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.estudo.dto.UsuarioDTO;
import br.com.estudo.exception.DelegateException;

public class LoginHelper {

	private static LoginHelper instance = null;

	private LoginHelper() {
	}

	public static LoginHelper getInstance() {
		if (instance == null)
			instance = new LoginHelper();
		return instance;
	}

	public boolean canlogin(UsuarioDTO usuario) throws DelegateException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext exc = fc.getExternalContext();
		if (usuario != null) {
			exc.getSessionMap().put("user", usuario);
			exc.getSessionMap().remove("notLogInUser");
			return true;
		}
		HttpServletRequest request = (HttpServletRequest) exc.getRequest();
		request.setAttribute("cantLogin", "invalidUserOrPassword");
		return false;
	}
}
