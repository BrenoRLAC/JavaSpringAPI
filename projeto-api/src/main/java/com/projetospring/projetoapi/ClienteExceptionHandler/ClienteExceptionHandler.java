package com.projetospring.projetoapi.ClienteExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projetospring.projetoapi.ClienteErrorMessage.ClienteErrorMessage;

@ControllerAdvice
public class ClienteExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request){
		String erroDescription = e.getMessage();
		if(erroDescription == null) erroDescription = e.toString();
		
		ClienteErrorMessage clienteErrorMessage = new ClienteErrorMessage(new Date(), erroDescription);
		return new ResponseEntity<>(clienteErrorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
