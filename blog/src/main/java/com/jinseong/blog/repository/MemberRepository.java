package com.jinseong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jinseong.blog.model.Member;

//DAO
//자동으로 bean 등록이 된다.
//@Repository 생략 가능하다.
public interface MemberRepository extends JpaRepository<Member, Integer>{

	//JPA Naming Query
	//SELECT * FROM member WHERE username = ?1 password =?2;
	//Member findByUsernameAndPassword(String username, String password);
	
	//@Query(value= "SELECT * FROM member WHERE username = ?1 password =?2", nativeQuery = true)
	//Member login(String username, String password);
	
}
