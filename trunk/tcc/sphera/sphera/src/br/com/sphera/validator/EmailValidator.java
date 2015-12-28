package br.com.sphera.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sphera.util.JSFUtil;

public class EmailValidator implements Validator{

	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		 String enteredEmail = (String)value;
		 Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher m = p.matcher(enteredEmail);
		 boolean matchFound = m.matches();
		if(!matchFound){
			FacesMessage message = JSFUtil.addErrorMessage("emailNotValid",component.getClientId(context));
			throw new ValidatorException(message);
		}
	}

}
