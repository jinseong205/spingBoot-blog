package com.jinseong.blog.service;

import java.security.Principal;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.model.Member;

@Service
public class WebSocketChattingService {

	public String getChatName() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.getName() == "anonymousUser") {
			return UUID.randomUUID().toString().replace("-", "");
		}else {
			return authentication.getName();
		}
	}
}
