package com.jinseong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	@GetMapping("member/joinForm")
	public String JoinForm() {
		return "member/joinForm";
	}
	
	@GetMapping("member/loginForm")
	public String LoginForm() {
		return "member/loginForm";
	}
}
