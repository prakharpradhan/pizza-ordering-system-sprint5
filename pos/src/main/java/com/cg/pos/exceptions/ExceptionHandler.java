package com.cg.pos.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(PizzaException.class)
	public ResponseEntity<ErrorDetails> exception(PizzaException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllWrongFormatExceptions(Exception e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails("Sorry Server Error", request.getDescription(true), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
