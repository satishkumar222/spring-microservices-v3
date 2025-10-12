package com.in28mintues.restful_web_service.helloworld.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28mintues.restful_web_service.helloworld.userdao.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails err=new ErrorDetails(LocalDate.now(),
				ex.getMessage(),request.getDescription(false));
		return new  ResponseEntity<ErrorDetails>(err,HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails err=new ErrorDetails(LocalDate.now(),
				ex.getMessage(),request.getDescription(false));
		return new  ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetails err=new ErrorDetails(LocalDate.now(),
				""+ex.getErrorCount()+ "" +ex.getFieldError().getDefaultMessage(),request.getDescription(false));
		return new  ResponseEntity(err,HttpStatus.BAD_REQUEST);	
	}
}
