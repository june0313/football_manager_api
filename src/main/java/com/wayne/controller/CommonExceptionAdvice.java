package com.wayne.controller;

import com.wayne.exception.DuplicatePlayerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wayne
 * @version 1.0
 * 컨트롤러에서 발생하는 예외를 처리한다.
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {

	/**
	 * 중복 회원 가입 예외 처리
	 * @param e DuplicatePlayerException
	 * @return ResponseEntity with bad request
	 */
	@ExceptionHandler(value = DuplicatePlayerException.class)
	public ResponseEntity<String> handleDuplicatePlayerException(DuplicatePlayerException e) {
		log.info(e.getMessage());
		return new ResponseEntity<>("duplicate player", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = IllegalStateException.class)
	public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
		log.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HttpMessageConversionException.class)
	public ResponseEntity<String> handleHttpMsgConversionException(HttpMessageConversionException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>("bad request.", HttpStatus.BAD_REQUEST);
	}

}
