package br.com.estudo.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -6733909250722560738L;

	public DAOException() {}
    
    public DAOException(String msg) {
    	super(msg);
    }
    
    public DAOException(String msg, Throwable cause) {
    	super(msg, cause);
    }
    
    public DAOException(Throwable cause) {
    	super(cause);
    }
}