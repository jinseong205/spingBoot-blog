package com.jinseong.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.dto.ResponseDto;
import com.jinseong.blog.model.Member;
import com.jinseong.blog.service.MemberService;

@RestController
public class MemberApiController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Member member) {
		int result = memberService.memberInsert(member);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PutMapping("/member")
	public ResponseDto<Integer> update(@RequestBody Member member, @AuthenticationPrincipal PrincipalDetail principal, HttpSession session){	//requestBody 없으면 key=value, x-www-form-unlencoded
		memberService.update(member);
		
		//세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
