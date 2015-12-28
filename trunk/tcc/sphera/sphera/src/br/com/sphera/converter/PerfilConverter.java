package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Perfil;
import br.com.sphera.fake.dao.PerfilDao;

public class PerfilConverter implements Converter{

	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		PerfilDao dao =(PerfilDao) context.getApplication().getELResolver().getValue(context.getELContext(), null, "daoPerfil");
		System.out.println(value);
		return dao.load(Long.valueOf(value));
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value==null?"-1":((Perfil)value).getId().toString();
	}

}
