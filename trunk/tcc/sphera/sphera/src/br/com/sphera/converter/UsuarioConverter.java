package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Usuario;
import br.com.sphera.fake.dao.UsuarioDAO;
import br.com.sphera.util.StringUtils;

public class UsuarioConverter implements Converter {

	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		UsuarioDAO dao = (UsuarioDAO)
		context.getApplication().getELResolver().getValue(context.getELContext(), null, "usuarioDAO");
		Usuario user = null;
		if(value.getClass().getSimpleName().equals("String")){
			String login = String.valueOf(value);
			if(!StringUtils.isEmpty(login)){
				login = login.substring(login.indexOf('(')+1,login.indexOf(')'));
				user = dao.findByLogin(login);
			}
		}
		return user;
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value == null ? "-1" : ((Usuario)value).toString();
	}

}
