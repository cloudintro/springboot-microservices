package com.smallintro.springboot.exception;

public class RecordAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public RecordAlreadyExistsException(String msg) {
		super(msg);
		
	}

}
