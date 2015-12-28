package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Cargo;
import br.com.sphera.fake.dao.CargoDAO;

public class CargoConverter implements Converter {

	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CargoDAO dao = (CargoDAO) context.getApplication().getELResolver().getValue(context.getELContext(),
				null, "cargoDAO");
		
		Long id = Long.valueOf(value);
		
		Cargo cargo = dao.load(id);
		
		return cargo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent compontent, Object value) {
		//Converte o valor passado como objeto para a string especificada, para isso
		// utiliza-se o id do objeto
		return value == null ? "-1" : ((Cargo) value).getId().toString();
	}

	
	
}
