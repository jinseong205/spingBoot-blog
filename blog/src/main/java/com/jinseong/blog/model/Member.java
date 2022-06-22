package com.jinseong.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //null 인값은 default value로 입력
@Entity	//User Class가 자동으로 Oracle에 생성
public class Member {
	
	@Id	//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // project에 연결된 db의 넘버링 전략을 따라간다.
	private int id;	//sequnce //auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;	//user_id

	@Column(nullable = false, length = 100) // hash (비밀번호 암호화)
	private String password;	//user_pw
	
	@Column( length = 50) 
	private String email;		
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;	//Enum을 쓰는게 좋음 //admin, user, manager
	
	@CreationTimestamp	//시간이 자동으로 입력
	private Timestamp createDate;
}
