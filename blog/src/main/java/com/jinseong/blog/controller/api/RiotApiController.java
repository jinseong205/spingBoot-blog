package com.jinseong.blog.controller.api;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jinseong.blog.service.RiotApiService;

@RestController
public class RiotApiController {

	@Value("${riot.api.key}")
	private String riotKey;
	
	@Value("${riot.api.summoner-url}")
	private String summonerUrl;
	
	
	@Autowired
	private RiotApiService riotApiService;
	
	@GetMapping("/api/riot-api/{username}")
	private String riotInfo(@PathVariable String username) throws Exception {
		
		RestTemplate rt = new RestTemplate();
		
		
		//UTF-8 인코딩
		byte[] bytes;
		try {
			 bytes = username.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("인코딩에 실패하였습니다." + e.getMessage());
		}
		String encodeUsername = new String(bytes);
		
		
		String summonerReqUrl = summonerUrl + encodeUsername;
		
		//header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
		headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("Origin","https://developer.riotgames.com");
		headers.add("X-Riot-Token", riotKey);
		
		//body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		// Hedaer + Body
		HttpEntity<MultiValueMap<String, String>> summonerReq = new HttpEntity<>(params, headers);
		
		ResponseEntity summonerResponseEntity = rt.exchange(summonerReqUrl, HttpMethod.GET, summonerReq, String.class);
		
		JSONObject summonerRes = null;
		
		
		return summonerResponseEntity.getBody().toString();	
	}
	
}
