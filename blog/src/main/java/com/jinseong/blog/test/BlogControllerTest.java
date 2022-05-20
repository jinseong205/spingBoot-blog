package com.jinseong.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Spring이 com.jinseong.blog package 이하 
//특정 annotation 이 붙은 class 파일을 메모리에 Spring Container에서 관리
@RestController 
public class BlogControllerTest {
	@GetMapping("test/hello")
	public String hello(){
		return "hello spring boot";
	}
}
