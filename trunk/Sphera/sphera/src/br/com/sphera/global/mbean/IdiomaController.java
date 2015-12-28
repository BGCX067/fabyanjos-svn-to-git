package br.com.sphera.global.mbean;

import java.util.Locale;

import javax.faces.context.FacesContext;

import br.com.sphera.util.StringUtils;


public class IdiomaController {

	private String language;
	private String country;
	private Locale lingua;
	
	public IdiomaController() {
		lingua = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	
	public void change(){
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		language = getLanguageParam();
		country = getCoutryParam();
		if(!StringUtils.isEmpty(country) && !StringUtils.isEmpty(language) ){
			locale = new Locale(language,country);
		}else if(!StringUtils.isEmpty(language)){
			locale = new Locale(language);
		}
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		lingua = locale;
	}
	
	private String getLanguageParam(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String param = (String) fc.getExternalContext().getRequestParameterMap().get("language");
		if (!StringUtils.isEmpty(param)) {
			return param;
		} else {
			return null;
		}
	}
	private String getCoutryParam(){
		FacesContext fc = FacesContext.getCurrentInstance();
		String param = (String) fc.getExternalContext().getRequestParameterMap().get("country");
		if (!StringUtils.isEmpty(param)) {
			return param;
		} else {
			return null;
		}
	}
	public Locale getLingua() {
		return lingua;
	}
}
