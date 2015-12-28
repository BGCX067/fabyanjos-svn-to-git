package br.com.grafos.exception;

public class InvalidArestaException extends Exception {

	private static final long serialVersionUID = 2084576526033890004L;
	public InvalidArestaException() {
		// TODO Auto-generated constructor stub
	}
	public InvalidArestaException(String message) {
		super(message);
	}
	public InvalidArestaException(Throwable e) {
		super(e);
	}
	public InvalidArestaException(String message,Throwable e) {
		super(message,e);
	}
}
