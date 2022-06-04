package com.jinseong.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	@Test
	public void hash_encoding() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234해쉬 : " + encPassword);
	}
}
