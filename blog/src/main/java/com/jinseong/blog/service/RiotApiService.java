package com.jinseong.blog.service;

import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.jinseong.blog.model.RiotInfo;

@Service	
public class RiotApiService {

	@Value("${riot.api.key}")
	private String riotKey;
	
	@Value("${riot.api.summoner-url}")
	private String summonerUrl;
	
	@Value("${riot.api.league-etnry-url}")
	private String leagueEntryUrl;
	
	@Value("${riot.api.match-by-puuid-url}")
	private String matchByPuuuidUrl;
	
	@Value("${riot.api.match-by-MatchId-url}")
	private String matchByMatchIdurl;

	
	public String riotInfo(String username) throws Exception {

		RestTemplate rt = new RestTemplate();
		HttpHeaders reqHeaders;
		MultiValueMap<String, String> reqBody;
		
		RiotInfo riotInfo = new RiotInfo();
		
		/* Summoner */
		
		//UTF-8 인코딩
		byte[] bytes;
		try {
			 bytes = username.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("인코딩에 실패하였습니다." + e.getMessage());
		}
		String encodeUsername = new String(bytes);
		String summonerReqUrl = summonerUrl.replace("{text}", encodeUsername);
		
		reqHeaders = new HttpHeaders();
		reqHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		reqHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
		reqHeaders.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		reqHeaders.add("Origin","https://developer.riotgames.com");
		reqHeaders.add("X-Riot-Token", riotKey);
		
		reqBody = new LinkedMultiValueMap<>();
		
		HttpEntity<MultiValueMap<String, String>> summonerReq = new HttpEntity<>(reqBody, reqHeaders);
		ResponseEntity<String> summonerResponseEntity = rt.exchange(summonerReqUrl, HttpMethod.GET, summonerReq, String.class);
		
		//System.out.println(summonerResponseEntity.getBody().toString());
		
		JSONObject sommonerJson = new JSONObject(summonerResponseEntity.getBody().toString());
		riotInfo.setId(sommonerJson.get("id").toString());
		riotInfo.setName(sommonerJson.get("puuid").toString());
		riotInfo.setPuuid(sommonerJson.get("name").toString());
		riotInfo.setSummonerLevel(sommonerJson.get("summonerLevel").toString());
		
		
		/* League Entry*/
		String leagueEntryReqUrl = leagueEntryUrl.replace("{text}", riotInfo.getId());
		
		ResponseEntity<String> leagueEntryEntity = rt.exchange(leagueEntryReqUrl, HttpMethod.GET, summonerReq, String.class);
		
		System.out.println(leagueEntryEntity.getBody());
		JSONObject leagueEntryJson = new JSONArray(leagueEntryEntity.getBody().toString()).getJSONObject(0);
		
		riotInfo.setTier(leagueEntryJson.get("tier").toString());
		riotInfo.setRank(leagueEntryJson.get("rank").toString());
		riotInfo.setLeaguePoints(leagueEntryJson.get("leaguePoints").toString());
		riotInfo.setWins(leagueEntryJson.get("wins").toString());
		riotInfo.setLosses(leagueEntryJson.get("losses").toString());
		
		/* match By puuuid*/
		String matchByPuuuidReqUrl = matchByPuuuidUrl.replace("{text}", riotInfo.getPuuid());
		
		String[] matchId = new String[5];
		
		
		
		
		return riotInfo.toString() + "<br/>" + leagueEntryEntity.getBody() ;	
	}
}
