package org.codejudge.sb.exception.handler;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.exception.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MovieExceptionHandler {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Error ex = new Error("failure", exception.getBindingResult().getFieldError().getDefaultMessage());
		log.error("MethodArgumentNotValidException occurred: {}", exception.getBindingResult().getFieldError());
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
		Error ex = new Error("failure", exception.getMessage());
		log.error("MissingServletRequestParameterException occurred: {}", exception.getMessage());
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler({ CommonException.class })
	public ResponseEntity<Object> handleCommonException(CommonException exception) {
		log.error("CommonException occurred: {}", exception.getError().getReason());
		return new ResponseEntity<Object>(exception.getError(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		log.error("HttpMessageNotReadableException occurred: {}", exception.getHttpInputMessage());
		Error ex = new Error("failure", "Required request body is missing");
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception exception) {
		log.error("Exception occurred: {}", exception.getMessage());
		Error ex = new Error("failure", "Unknown Exception Occured, please retry again");
		return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
