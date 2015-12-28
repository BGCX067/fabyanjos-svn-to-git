package br.com.estudo.exception;

public class BussinessException extends Exception {
	private static final long serialVersionUID = 1585694837568713166L;

	public BussinessException() {
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
