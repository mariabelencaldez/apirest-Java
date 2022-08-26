package com.aula.services.exceptions;


public class OperacaoNaoAutorizadaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public OperacaoNaoAutorizadaException(String msg) {
		super(msg);
	}
	
	
}
