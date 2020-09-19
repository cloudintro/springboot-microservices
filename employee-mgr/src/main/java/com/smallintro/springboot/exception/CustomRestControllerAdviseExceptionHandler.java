package com.smallintro.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdviseExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails recordNotFound(RecordNotFoundException ex) {

		return new CustomErrorDetails(new Date(), "No record found with matching query parameters.", ex.getMessage());

	}

}
