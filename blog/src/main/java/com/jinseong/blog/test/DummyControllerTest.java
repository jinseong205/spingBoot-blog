package com.jinseong.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.MemberRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired	//의존성 주입(DI) >> 메모리 할당
	private MemberRepository memberRpository;
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http 의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(Member m) {
		
		System.out.println("id : " + m.getId());
		System.out.println("username : " + m.getUsername());
		System.out.println("password : " + m.getPassword());
		System.out.println("email : " + m.getEmail());
		System.out.println("role : " + m.getRole());
		System.out.println("createDate : " + m.getCreateDate());
		
		m.setRole(RoleType.USER);
		memberRpository.save(m);
		
		return "complete";
	}
}
