package com.jinseong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinseong.blog.model.Member;

//DAO
//자동으로 bean 등록이 된다.
//@Repository 생략 가능하다.
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
}
