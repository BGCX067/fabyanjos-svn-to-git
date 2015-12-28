package br.com.estudo.exception;

public class DelegateException extends Exception {
	private static final long serialVersionUID = 1585694837568713166L;

	public DelegateException() {
	}

	public DelegateException(String message) {
		super(message);
	}

	public DelegateException(Throwable cause) {
		super(cause);
	}

	public DelegateException(String message, Throwable cause) {
		super(message, cause);
	}

}
