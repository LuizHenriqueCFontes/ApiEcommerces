package com.estudos.ecommerce.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<Object> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
		Map<String, Object> body = new LinkedHashMap();
		
		body.put("timestamp", LocalDate.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Usuario não encontrado");
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		
	}

}
