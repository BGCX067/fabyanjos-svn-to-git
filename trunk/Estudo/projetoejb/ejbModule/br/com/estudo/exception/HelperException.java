package br.com.estudo.exception;

public class HelperException extends Exception {
	private static final long serialVersionUID = 1585694837568713166L;

	public HelperException() {
	}

	public HelperException(String message) {
		super(message);
	}

	public HelperException(Throwable cause) {
		super(cause);
	}

	public HelperException(String message, Throwable cause) {
		super(message, cause);
	}

}
