package com.almeida.os.resource.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {


	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;
	
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFildName() {
		return fieldName;
	}

	public void setFildName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
		
}
