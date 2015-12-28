package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Acesso;
import br.com.sphera.fake.dao.AcessoDao;

public class AcessoConverter implements Converter{

	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		AcessoDao dao =(AcessoDao) context.getApplication().getELResolver().getValue(context.getELContext(), null, "daoAcesso");
		System.out.println(value);
		return dao.load(Long.valueOf(value));
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value==null?"-1":((Acesso)value).getId().toString();
	}

}
