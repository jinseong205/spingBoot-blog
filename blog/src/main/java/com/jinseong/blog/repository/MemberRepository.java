package com.jinseong.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinseong.blog.model.Member;

//DAO
//자동으로 bean 등록이 된다.
//@Repository 생략 가능하다.
public interface MemberRepository extends JpaRepository<Member, Integer>{
	//SELECT * FROM member WHERE username= 1?;
	Optional<Member> findByUsername(String username); 
	
	//JPA Naming Query
	//SELECT * FROM member WHERE username = ?1 password =?2;
	//Member findByUsernameAndPassword(String username, String password);
	
	//@Query(value= "SELECT * FROM member WHERE username = ?1 password =?2", nativeQuery = true)
	//Member login(String username, String password);
	
}
