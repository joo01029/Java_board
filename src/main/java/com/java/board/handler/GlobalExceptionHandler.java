package com.java.board.handler;


import com.java.board.domain.response.Response;
import com.java.board.exception.CustomException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Response> handleCustomException(CustomException e){
		Response response = new Response(e.getStatus().value(), e.getMessage());

		return new ResponseEntity<Response>(response, e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception e){
		e.printStackTrace();
		Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에러");

		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleValidException(MethodArgumentNotValidException e){
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
