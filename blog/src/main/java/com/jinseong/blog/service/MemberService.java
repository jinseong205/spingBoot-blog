package com.jinseong.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public int memberInsert(Member member) {
		try {
			memberRepository.save(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberService:회원가입()" + e.getMessage());
		}
		return -1;
	}
	//select 할 때 트랜잭션 시작. 서비스 종료시에 트랜잭션 종료
	@Transactional(readOnly=true) 
	public Member login(Member member) {
		return memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword());
	}
}