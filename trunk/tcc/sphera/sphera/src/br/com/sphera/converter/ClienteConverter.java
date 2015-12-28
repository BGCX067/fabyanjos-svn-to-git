package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Cliente;
import br.com.sphera.fake.dao.ClienteDAO;
import br.com.sphera.util.JSFUtil;

public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		ClienteDAO dao = (ClienteDAO) JSFUtil.getManagedBean("clienteDAO");
		Cliente cliente = null;
		Long valor = Long.valueOf(value);
		
		if(valor > 0)
			cliente = dao.load(valor);
		return cliente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value == null ?"-1":((Cliente)value).getId().toString();
	}

}
