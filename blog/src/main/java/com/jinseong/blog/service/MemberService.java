package com.jinseong.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.repository.MemberRepository;


@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	public int memberInsert(Member member){
		try{
			memberRepository.save(member);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberService:회원가입()" + e.getMessage());
		}
		return -1;
	}
}
