package com.alfa.tracking.spring.helper;

import java.util.ArrayList;
import java.util.List;

public class Response {

	private boolean sucess = true;
	private List<Error> errors = new ArrayList<Error>();

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public List<Error> getErrors() {
		return errors;
	}

}
