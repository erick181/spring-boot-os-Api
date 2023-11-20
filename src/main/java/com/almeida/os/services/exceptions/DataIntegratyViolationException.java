package com.almeida.os.services.exceptions;

import java.io.Serializable;

public class DataIntegratyViolationException extends RuntimeException implements Serializable{


	private static final long serialVersionUID = 1L;

	public DataIntegratyViolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataIntegratyViolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
