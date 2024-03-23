package com.ou_solutions.employeetesting.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { Exception.class,UsernameNotFoundException.class})
	public ResponseEntity<String> exceptionHandle(Exception e)
	{
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR).badRequest().body( e.getMessage());
	}

}
