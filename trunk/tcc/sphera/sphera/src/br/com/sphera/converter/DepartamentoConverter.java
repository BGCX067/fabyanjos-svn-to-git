package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Departamento;
import br.com.sphera.fake.dao.DepartamentoDAO;

public class DepartamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		DepartamentoDAO dao = (DepartamentoDAO)context.getApplication().getELResolver().getValue(context.getELContext(), null, "deptDAO");
		Departamento departamento = dao.load(Long.valueOf(value));
		return departamento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "-1";			
		}
		return ((Departamento)value).getId().toString();
			
	}

}
