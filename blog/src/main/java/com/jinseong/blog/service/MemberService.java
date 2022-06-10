package com.jinseong.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
	@Transactional
	public int memberInsert(Member member) {

		String rawPassword = member.getPassword();
		String encPassword = encoder.encode(rawPassword);
		member.setPassword(encPassword);
		member.setRole(RoleType.USER);
		
		try {
			memberRepository.save(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberService:회원가입()" + e.getMessage());
		}
		return -1;
	}

	@Transactional
	public void update(Member reqMember) {
		Member member = memberRepository.findById(reqMember.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패.");
				});
		String rawPassword = reqMember.getPassword();
		String encPassword = encoder.encode(rawPassword);
		member.setPassword(encPassword);
		member.setEmail(reqMember.getEmail());

	
		//회원 수정 함수 종료시 = 서비스종료 = 트랜잭션 종료 = commit
		//영속화된 persistance 객체의 변화가 감지 되면 더티 체킹이 되어 update문을 날려줌
	}
	
	/*
	//select 할 때 트랜잭션 시작. 서비스 종료시에 트랜잭션 종료
	@Transactional(readOnly=true) 
	public Member login(Member member) {
		return memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword());
	}
	*/
}
