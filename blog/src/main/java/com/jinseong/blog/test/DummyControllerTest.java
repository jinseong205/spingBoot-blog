package com.jinseong.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.MemberRepository;

@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI) >> 메모리 할당
	private MemberRepository memberRpository;

	/// http://localhost:8000/blog/dummy/member/3
	// {id} 주소로 param을 전달 받을 수 있음
	@GetMapping("/dummy/member/{id}")
	public Member Detail(@PathVariable int id) {
		/*
		 * http://localhost:8000/blog/dummy/member/4 을 찾으면 db에서 못찾아오게 되면 member는 null
		 * Optional로 member 객체를 가져와서 null 판단 .orElseThrow() 으로 exception 발생
		 */
		Member member = memberRpository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
			}
		});
		
		return member;
	}

	// http://localhost:8000/blog/dummy/join(요청)
	// http 의 body에 username, password, email 데이터를 가지고 요청
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
