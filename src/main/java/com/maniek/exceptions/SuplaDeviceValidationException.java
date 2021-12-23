package com.maniek.exceptions;

import org.springframework.http.HttpStatus;

public class SuplaDeviceValidationException extends RuntimeException {
	private HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public SuplaDeviceValidationException(HttpStatus httpStatus, String message) {
		
		super(message);
		this.httpStatus = httpStatus;
		
	}
}
