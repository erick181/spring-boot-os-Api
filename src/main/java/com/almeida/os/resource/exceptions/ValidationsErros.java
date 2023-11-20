package com.almeida.os.resource.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationsErros extends StandardError implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationsErros(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

	
}
