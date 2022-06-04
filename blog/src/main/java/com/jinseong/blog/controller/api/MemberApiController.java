package com.jinseong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.B2CConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.dto.ResponseDto;
import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.service.MemberService;

@RestController
public class MemberApiController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private MemberService memberService;

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Member member) {
		System.out.println("save 호출됨");
		int result = memberService.memberInsert(member);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

	
	/*
	@PostMapping("/api/member/login")
	public ResponseDto<Integer> login(@RequestBody Member member, HttpSession session) {
		System.out.println("login 호출됨");
		
		//DB에 insert를 하고 return
		member.setRole(RoleType.USER);
		Member principal = memberService.login(member);	//principal (접근주체)
		
		if(principal != null) {
			session.setAttribute("princaipal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	*/

}
