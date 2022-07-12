package com.jinseong.blog.service;

import java.io.UnsupportedEncodingException;

import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.jinseong.blog.model.RiotInfo;

import reactor.core.publisher.Mono;

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

		// bodyData
		// MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

		// WebClient client =
		// WebClient.create("https://asia.api.riotgames.com/lol/match/v5/matches/KR_6008777525");

		WebClient client = WebClient.create();

		Mono<String> a = client.get().uri("https://asia.api.riotgames.com/lol/match/v5/matches/KR_6008777525").headers(headers -> {
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Origin", "https://developer.riotgames.com");
			headers.add("X-Riot-Token", riotKey);
		}).retrieve().bodyToMono(String.class);
		
//		System.out.println(a);
//		
//		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
//				HttpClients.createDefault());
//		RestTemplate rt = new RestTemplate(requestFactory);
//
//		HttpHeaders reqHeaders;
//		MultiValueMap<String, String> reqBody;
//
//		RiotInfo riotInfo = new RiotInfo();
//
//		/* Header + Body */
//		reqHeaders = new HttpHeaders();
//		reqHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//		reqHeaders.add("User-Agent",
//				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
//		reqHeaders.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
//		reqHeaders.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
//		reqHeaders.add("Content-Encoding", "gzip");
//		reqHeaders.add("Origin", "https://developer.riotgames.com");
//		reqHeaders.add("X-Riot-Token", riotKey);
//
//		reqBody = new LinkedMultiValueMap<>();
//
//		HttpEntity<MultiValueMap<String, String>> HttpEntity = new HttpEntity<>(reqBody, reqHeaders);
//
////		HttpEntity<MultiValueMap<String, String>> HttpEntity = new HttpEntity<>(reqBody, reqHeaders);
//
//		byte[] bytes;
//		try {
//			bytes = "KR_6008777525".getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new UnsupportedEncodingException("인코딩에 실패하였습니다." + e.getMessage());
//		}
//		String matchGame = new String(bytes);
//
//		try {
//			System.out.println(matchGame);
//			rt.exchange("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchGame, HttpMethod.GET, HttpEntity,
//					String.class);
//			// System.out.println(a.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		/* Summoner */
//
//		// UTF-8 인코딩
//		byte[] bytes;
//		try {
//			bytes = username.getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new UnsupportedEncodingException("인코딩에 실패하였습니다." + e.getMessage());
//		}
//		String encodeUsername = new String(bytes);
//		String summonerReqUrl = summonerUrl.replace("{text}", encodeUsername);
//		ResponseEntity<String> summonerResponseEntity = rt.exchange(summonerReqUrl, HttpMethod.GET, HttpEntity,
//				String.class);
//
//		// System.out.println(summonerResponseEntity.getBody().toString());
//		JSONObject sommonerJson = new JSONObject(summonerResponseEntity.getBody());
//		riotInfo.setId(sommonerJson.get("id").toString());
//		riotInfo.setPuuid(sommonerJson.get("puuid").toString());
//		riotInfo.setName(sommonerJson.get("name").toString());
//		riotInfo.setSummonerLevel(sommonerJson.get("summonerLevel").toString());
//
//		/* League Entry */
//		String leagueEntryReqUrl = leagueEntryUrl.replace("{text}", riotInfo.getId());
//		ResponseEntity<String> leagueEntryEntity = rt.exchange(leagueEntryReqUrl, HttpMethod.GET, HttpEntity,
//				String.class);
//
//		// System.out.println(leagueEntryEntity.getBody());
//		JSONObject leagueEntryJson = new JSONArray(leagueEntryEntity.getBody()).getJSONObject(0);
//		riotInfo.setTier(leagueEntryJson.get("tier").toString());
//		riotInfo.setRank(leagueEntryJson.get("rank").toString());
//		riotInfo.setLeaguePoints(leagueEntryJson.get("leaguePoints").toString());
//		riotInfo.setWins(leagueEntryJson.get("wins").toString());
//		riotInfo.setLosses(leagueEntryJson.get("losses").toString());
//
//		/* match By puuuid */
//		String matchByPuuuidReqUrl = matchByPuuuidUrl.replace("{text}", riotInfo.getPuuid());
//		ResponseEntity<String> matchByPuuuidEntity = rt.exchange(matchByPuuuidReqUrl, HttpMethod.GET, HttpEntity,
//				String.class);
//
//		String[] matchIds = new String[5];
//		matchIds = matchByPuuuidEntity.getBody().split(",");
//
//		/* match By matchById */
//		String matchId;
//		String matchByMatchIdReqUrl;
//		ResponseEntity<String> matchByMatchIdEntity;
//
//		List<JSONObject> matchs = new ArrayList<JSONObject>();
//
//		for (String temp : matchIds) {
//			matchId = temp.replace("[", "").replace("]", "").replaceAll("\"", "").trim();
//
//			try {
//				bytes = matchId.getBytes("UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				throw new UnsupportedEncodingException("인코딩에 실패하였습니다." + e.getMessage());
//			}
//			String encodeMatchId = new String(bytes);
//
//			matchByMatchIdReqUrl = matchByMatchIdurl.replace("{text}", encodeMatchId);
//			System.out.println("matchId : " + matchId + " : " + matchByMatchIdReqUrl);
//
//			matchByMatchIdEntity = rt.exchange(matchByMatchIdReqUrl, HttpMethod.GET, HttpEntity, String.class);
//			System.out.println("matchId : " + matchId + matchByMatchIdEntity.getBody());
//			matchs.add(new JSONObject(matchByMatchIdEntity.getBody()));
//		}
//
//		riotInfo.setMatchs(matchs);

		return a.block() + "<br/>";
	}
}
