package com.alfa.tracking.exception;

public class ConsultaException extends Exception {

	private static final long serialVersionUID = ConsultaException.class.getName().hashCode();

	public ConsultaException() {
	}

	public ConsultaException(String message) {
		super(message);
	}

	public ConsultaException(Throwable cause) {
		super(cause);
	}

	public ConsultaException(String message, Throwable cause) {
		super(message, cause);
	}

}
