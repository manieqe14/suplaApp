package com.maniek.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(SuplaDeviceValidationException.class)
	public ResponseEntity handleException(SuplaDeviceValidationException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}

}
