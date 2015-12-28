package br.com.sphera.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.sphera.entities.Fase;
import br.com.sphera.fake.dao.FaseDao;
import br.com.sphera.util.JSFUtil;

public class FaseConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String value) {
		FaseDao dao = (FaseDao) JSFUtil.getManagedBean("daoFase");
		Fase fase=null;
		if(value.getClass().getSimpleName().equals("String")){
			String valor = String.valueOf(value);
			String projetoName = valor.substring(valor.indexOf('(')+1,valor.indexOf(')'));
			String faseName = valor.substring(0,valor.indexOf('('));
			fase = dao.findFaseNameAndProjeName(faseName.trim(), projetoName);
		}
		return fase;
	}

	@Override
	public String getAsString(FacesContext cont, UIComponent comp, Object value) {
		return value == null ? "": ((Fase)value).toString();
	}

}
