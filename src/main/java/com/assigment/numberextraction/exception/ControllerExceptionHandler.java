package com.assigment.numberextraction.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
	    
	    return message;
	}
}
