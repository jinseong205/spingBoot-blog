package com.jinseong.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jinseong.blog.model.Member;
import com.jinseong.blog.repository.MemberRepository;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	//	Spring이 로그인 요청을 가로챌때, username, password 변수 2개를 가로채는데
	//	password 부분 처리는 알아서 함
	//	username이 db에 있는지만 확인해서 return
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member principal = memberRepository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
				});
		return new PrincipalDetail(principal);	//시큐리티의 세션에 유저 정보가 저장이 됨.
	}
	
}
