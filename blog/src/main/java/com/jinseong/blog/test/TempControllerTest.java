package com.jinseong.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	//http://localhost:8000/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		//file return default path : src/main/resources/staic
		//return file : /home.html
		//full path : src/main/resource
		System.out.println("tempHome()");
		return "/home.html";
	}
	
	@GetMapping("temp/jinseong")
	public String tempImg() {
		return "/jinseong.jpg";
	}
	
	@GetMapping("temp/jsp")
	//prefix : /WEB-INF/views/
	//suffix: .jsp
	//full path : /WEB-INF/views/test.jsp
	public String tempJsp() {
		return "/test";
	}
}
