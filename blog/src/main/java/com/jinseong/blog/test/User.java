package com.jinseong.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//model 만들시 변수는 private 
//상태값을 변경 하기 위해서는 getter setter
 
@Data // getter, setter
//@AllArgsConstructor //모든 arg에 대한 생성자
@NoArgsConstructor // none arg에 대한 생성자
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder //개별 arg에 대한 builder 지원하여 생성가능
	public User(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
