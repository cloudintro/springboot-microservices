package com.smallintro.springboot.exception;

import java.util.Date;

public class CustomErrorDetails {
	Date date;
	String message;
	String errordetail;

	public CustomErrorDetails(Date date, String message, String errordetail) {
		super();
		this.date = date;
		this.message = message;
		this.errordetail = errordetail;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getErrordetail() {
		return errordetail;
	}

	
	

}
