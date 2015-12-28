package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Projeto;
import br.com.sphera.fake.dao.ProjetoDAO;
import br.com.sphera.util.JSFUtil;

public class ProjetoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String value) {
		ProjetoDAO dao = (ProjetoDAO) JSFUtil.getManagedBean("projetoDAO");
		Projeto projeto=null;
		if(value.getClass().getSimpleName().equals("String")){
			String valor = String.valueOf(value);
			String cliente = valor.substring(valor.indexOf('(')+1,valor.indexOf(')'));
			String proj = valor.substring(0,valor.indexOf('('));
			projeto = dao.findProjetoByNameAndNameCliente(proj.trim(), cliente);
		}
		return projeto;
	}

	@Override
	public String getAsString(FacesContext cont, UIComponent comp, Object value) {
		return value == null ? "": ((Projeto)value).toString();
	}

}
