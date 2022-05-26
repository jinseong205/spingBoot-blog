package com.jinseong.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHanddler {
	@ExceptionHandler(value=Exception.class)
	public String hadnleArgumentException(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
