package com.example.exchange.config.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = {ClientException.class})
    protected ResponseEntity<Object> handleClientException(
    		ClientException ex, WebRequest request) {
    	ErrorDetails details = buildErrorDetails(ex.getMessage());
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(
    		ObjectNotFoundException ex, WebRequest request) {
    	ErrorDetails details = buildErrorDetails(ex.getMessage());
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalException(
    		Exception ex, WebRequest request) {
    	ErrorDetails details = buildErrorDetails("Service Unavailable");
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

	private ErrorDetails buildErrorDetails(String message) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(message);
    	errorDetails.setTimestamp(new Date());
    	return errorDetails;
	}
    
    
}

