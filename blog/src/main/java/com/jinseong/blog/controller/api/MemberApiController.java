package com.jinseong.blog.controller.api;

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
	private MemberService memberService;
	
	@PostMapping("/api/member")
	public ResponseDto<Integer> save(@RequestBody Member member) {
		System.out.println("save 호출됨");
		
		System.out.println(member.getUsername());
		//DB에 insert를 하고 return
		member.setRole(RoleType.USER);
		int result = memberService.memberInsert(member);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
