package com.jinseong.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinseong.blog.auth.PrincipalDetail;


//인증이 안된 사용자들이 출입 할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/**, /css/**, /image/**
@Controller
public class MemberController {

	@GetMapping("/auth/joinForm")
	public String JoinForm() {
		return "member/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String LoginForm() {
		return "member/loginForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoLoginCallback(String code) { //Data를 리턴해주는 컨트롤러 함수
		return "카카오 인증 완료 : " + code;
	}
	
	
	@GetMapping("/member/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "member/updateForm";
	}
}
