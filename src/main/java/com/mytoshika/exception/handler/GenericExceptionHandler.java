package com.mytoshika.exception.handler;

import java.util.Optional;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mytoshika.dto.GenericResponse;
import com.mytoshika.dto.MessageValidation;
import com.mytoshika.dto.Status;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Optional<String> errorOptional = ex.getBindingResult().getFieldErrors().
				stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst();

		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		errorOptional.ifPresent(body::setError);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(MessageValidation.WRONG);

		log.info("Error in handleOtherException, ex: {}", ex);
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ResponseStatusException.class)
	public ResponseEntity<Object> handleBadRequestValidation(ResponseStatusException ex, WebRequest request) {
		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(ex.getReason());
		return new ResponseEntity<>(body, ex.getStatus());
	}
}
