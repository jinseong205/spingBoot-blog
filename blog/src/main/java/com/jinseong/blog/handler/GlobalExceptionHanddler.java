package com.jinseong.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHanddler {
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> hadnleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
