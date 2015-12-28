package br.com.estudo.exception;

public class ServiceLocatorException extends Exception {
	private static final long serialVersionUID = 1585694837568713166L;

	public ServiceLocatorException() {
	}

	public ServiceLocatorException(String message) {
		super(message);
	}

	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}

	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
