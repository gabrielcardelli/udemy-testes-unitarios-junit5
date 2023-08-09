package br.ce.wcaquino.barriga.dominio.exceptions;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 3427213835606393408L;

	public ValidationException(String message) {
		super(message);
	}
	
}
