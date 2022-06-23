package com.jinseong.blog.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jinseong.blog.model.KakaoProfile;
import com.jinseong.blog.model.Member;
import com.jinseong.blog.model.Member.MemberBuilder;
import com.jinseong.blog.model.OAuthToken;
import com.jinseong.blog.model.RoleType;
import com.jinseong.blog.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${kakao.login-key}")
	private String kakaoLoingKey;
	
	@Value("${kakao.client-id}")
	private String kakaoClientId;
	
	@Value("${kakao.redirect-uri}")
	private String kakaoRedirectUri;

	@Value("${kakao.logout-uri}")
	private String kakaoLogoutUri;
	
	@Value("${kakao.token-req-uri}")
	private String kakaoTokenReqUri;
	
	@Value("${kakao.user-info-uri}")
	private String kakaoUserInfoUri;
	

	@Transactional
	public int memberInsert(Member member) {

		String rawPassword = member.getPassword();
		String encPassword = encoder.encode(rawPassword);
		member.setPassword(encPassword);
		member.setRole(RoleType.USER);

		try {
			memberRepository.save(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public void update(Member reqMember) {
		Member member = memberRepository.findById(reqMember.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패.");
		});

		if (member.getOauth() == null || member.getOauth().equals("")) {
			String rawPassword = reqMember.getPassword();
			String encPassword = encoder.encode(rawPassword);
			member.setPassword(encPassword);
		}
		member.setEmail(reqMember.getEmail());

		// 회원 수정 함수 종료시 = 서비스종료 = 트랜잭션 종료 = commit
		// 영속화된 persistance 객체의 변화가 감지 되면 더티 체킹이 되어 update문을 날려줌
	}

	@Transactional(readOnly = true)
	public Member memberFindOne(String username) {
		Member member = null;
		try {
			member = memberRepository.findByUsername(username).get();
		} catch (Exception e) {
			member = null;
		}

		return member;
	}

	@Transactional(readOnly = true)
	public int kakaoLogin(String code) {

		/* kakao token req */

		// POST방식으로 key=value 데이터를 요청 (to 카카오)
		RestTemplate rt = new RestTemplate();

		// Headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", kakaoRedirectUri);
		params.add("code", code);

		// Hedaer + Body
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식 response 변수의 응답을 받음
		ResponseEntity response = rt.exchange(kakaoTokenReqUri, HttpMethod.POST, kakaoTokenReq,
				String.class);

		// Gson, Json Simple, ObejctMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = objectMapper.readValue(response.getBody().toString(), OAuthToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		/* kakao profile req */
		RestTemplate rt2 = new RestTemplate();

		// Headers
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Hedaer
		HttpEntity<MultiValueMap<String, String>> kakaoProfileReq2 = new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식 response 변수의 응답을 받음
		ResponseEntity response2 = rt2.exchange(kakaoUserInfoUri, HttpMethod.POST, kakaoProfileReq2,
				String.class);

		// Gson, Json Simple, ObejctMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		objectMapper2.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // 네이밍 전략 추가 (Snake -> Ca
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper.readValue(response2.getBody().toString(), KakaoProfile.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tempPassword = kakaoLoingKey;
		// String encPassword = encoder.encode(tempPassword.toString());

		Member kakaoMember = Member.builder().username("kakao_" + kakaoProfile.getId())
				.password(tempPassword.toString()).email(kakaoProfile.getKakao_account().getEmail()).role(RoleType.USER)
				.oauth("kakao").build();

		Member member = memberFindOne(kakaoMember.getUsername());

		if (member == null) {
			memberInsert(kakaoMember);
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoMember.getUsername(), tempPassword));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return 1;
	}

	// select 할 때 트랜잭션 시작. 서비스 종료시에 트랜잭션 종료
	// @Transactional(readOnly = true)
	// public Member login(Member member) {
	// return memberRepository.findByUsernameAndPassword(member.getUsername(),
	// member.getPassword());
	// }

}
