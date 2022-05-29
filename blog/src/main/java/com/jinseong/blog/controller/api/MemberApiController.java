package com.jinseong.blog.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.model.Member;

@RestController
public class MemberApiController {
	@PostMapping("/api/member")
	public int save(@RequestBody Member member) {
		System.out.println("save 호출됨");
		return 1;
	}
}
