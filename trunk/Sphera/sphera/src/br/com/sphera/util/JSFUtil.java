package br.com.sphera.util;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JSFUtil {

	/**
	 * @return o External Context
	 */
	public static ExternalContext getExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext();
	}

	/**
	 * @param parameterName
	 * @return um parametro na request com o nome informado
	 */
	public static String getRequestParameter(String parameterName) {
		Map<String, String> paramMap = getExternalContext().getRequestParameterMap();
		return (String) paramMap.get(parameterName);
	}

	/**
	 * @param attributeName
	 * @return um objeto na request de acordo com o nome passado
	 */
	public static Object getRequestAttribute(String attributeName) {
		Map<String, Object> attrMap = getExternalContext().getRequestMap();
		return attrMap.get(attributeName);
	}

	/**
	 * @param attributeName
	 * @return um objeto na sessão de acordo como nome passado
	 */
	public static Object getSessionAtribute(String attributeName) {
		Map<String, Object> attrMap = getExternalContext().getSessionMap();
		return attrMap.get(attributeName);
	}

	/**
	 * @param attributeName
	 * @return uma atributo na aplicação, de acordo com o nome passado
	 */
	public static Object getApplicationAttribute(String attributeName) {
		Map<String, Object> reqAttrMap = getExternalContext().getApplicationMap();
		return reqAttrMap.get(attributeName);
	}

	/**
	 * Insere uma mensagem de erro na aplicação, para um componete especifico, com o id
	 * passado no paramentro
	 * @param key a chave que está no bundle
	 * @param id id do componente a ser imbutido erro
	 * @param args os argumantos a complematar de acordo com {@link MessageFormat}
	 */
	public static FacesMessage addErrorMessage(String key, String id, Object... args) {
		ResourceBundle bundle = getBundle();
		String message = StringUtils.fillArgs(bundle.getString(key), args);
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
		FacesContext.getCurrentInstance().addMessage(id,
				facesMsg);
		return facesMsg;
	}

	/**
	 * @return o bundle registrado faces-config
	 */
	public static ResourceBundle getBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		String messageBundle = context.getApplication().getMessageBundle();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, context
				.getViewRoot().getLocale());
		return bundle;
	}
	/**
	 * Insere uma mensagem de informação na aplicação, para um componete especifico, com o id
	 * passado no parametro
	 * @param key a chave que está no bundle
	 * @param id id do componente a ser imbutido erro
	 * @param args os argumantos a complematar de acordo com {@link MessageFormat}
	 */
	public static FacesMessage  addInfoMessage(String key,String id, Object... args) {
		ResourceBundle bundle = getBundle();
		String message = StringUtils.fillArgs(bundle.getString(key), args);
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
		FacesContext.getCurrentInstance().addMessage(id,
				facesMsg);
		return facesMsg;
	}
	public static Object getManagedBean(String name){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getELResolver().getValue(context.getELContext(), null, name);
	}
	
}
