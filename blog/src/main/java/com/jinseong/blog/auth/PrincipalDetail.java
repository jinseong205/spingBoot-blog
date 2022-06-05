package com.jinseong.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jinseong.blog.model.Member;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails{
	private Member member; //컴포지션
	
	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}
	// 계정이 만료되지 않았는지 리턴한다. (true: 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 계정이 잠겨있지 않았는지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//비밀 번호 만료되지 않았는지 리턴한다. (true: 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//계정이 활성화(사용가능)인지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	//
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
		
		collectors.add(()->{return "ROLE_"+member.getRole();});
		
		/*
		collectors.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_" + member.getRole();	//ROLE_USER *prefix 필수
			}
		}
		*/
		return collectors;
	}
	
	
}
