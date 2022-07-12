package com.jinseong.blog.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jinseong.blog.model.RiotInfo;
import com.jinseong.blog.model.RiotMatch;

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

	public RiotInfo riotInfoDummy() throws Exception {
		RiotInfo riotInfo = new RiotInfo();

		return riotInfo;

	}

	public RiotInfo riotInfo(String username) throws Exception {

		RiotInfo riotInfo = new RiotInfo();

		WebClient client = WebClient.create();

		/* Summoner */
		String summonerReqUrl = summonerUrl.replace("{text}", username);

		Mono<String> monoString = client.get().uri(summonerReqUrl).headers(headers -> {
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Origin", "https://developer.riotgames.com");
			headers.add("X-Riot-Token", riotKey);
		}).retrieve().bodyToMono(String.class);

		JSONObject sommonerJson = new JSONObject(monoString.block());

		riotInfo.setId(sommonerJson.get("id").toString());
		riotInfo.setPuuid(sommonerJson.get("puuid").toString());
		riotInfo.setName(sommonerJson.get("name").toString());
		riotInfo.setSummonerLevel(sommonerJson.get("summonerLevel").toString());

		/* League Entry */
		String leagueEntryReqUrl = leagueEntryUrl.replace("{text}", riotInfo.getId());
		monoString = client.get().uri(leagueEntryReqUrl).headers(headers -> {
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Origin", "https://developer.riotgames.com");
			headers.add("X-Riot-Token", riotKey);
		}).retrieve().bodyToMono(String.class);

		boolean isEntry = new JSONArray(monoString.block()).length() > 0;

		if (isEntry) {
			JSONObject leagueEntryJson = new JSONArray(monoString.block()).getJSONObject(0);
			riotInfo.setTier(leagueEntryJson.get("tier").toString());
			riotInfo.setRank(leagueEntryJson.get("rank").toString());
			riotInfo.setLeaguePoints(leagueEntryJson.get("leaguePoints").toString());
			riotInfo.setWins(leagueEntryJson.get("wins").toString());
			riotInfo.setLosses(leagueEntryJson.get("losses").toString());
		}
		/* match By puuuid */
		String matchByPuuuidReqUrl = matchByPuuuidUrl.replace("{text}", riotInfo.getPuuid());
		monoString = client.get().uri(matchByPuuuidReqUrl).headers(headers -> {
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
			headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
			headers.add("Origin", "https://developer.riotgames.com");
			headers.add("X-Riot-Token", riotKey);
		}).retrieve().bodyToMono(String.class);

		String[] matchIds = new String[5];
		matchIds = monoString.block().split(",");

		String matchByMatchIdReqUrl;
		String matchId;

		List<RiotMatch> matchs = new ArrayList<RiotMatch>();
		
		RiotMatch riotMatch = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		
		for (int i = 0; i < matchIds.length; i++) {
			matchId = matchIds[i].replace("[", "").replace("]", "").replaceAll("\"", "").trim();

			matchByMatchIdReqUrl = matchByMatchIdurl.replace("{text}", matchId);

			monoString = client.get().uri(matchByMatchIdReqUrl).headers(headers -> {
				headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				headers.add("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
				headers.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
				headers.add("Origin", "https://developer.riotgames.com");
				headers.add("X-Riot-Token", riotKey);
			}).retrieve().bodyToMono(String.class);

			JSONObject matchInfoJson = new JSONObject(monoString.block()).getJSONObject("info");
			
			riotMatch = new RiotMatch(); 
			
			Timestamp stratTimeStamp = new Timestamp(Long.parseLong(matchInfoJson.get("gameStartTimestamp").toString()));
			Timestamp endTimeStmpa = new Timestamp(Long.parseLong(matchInfoJson.get("gameEndTimestamp").toString()));
			
			riotMatch.setGameMode(matchInfoJson.getString("gameMode"));
			riotMatch.setGameStartTimestamp(sdf.format(stratTimeStamp));
			riotMatch.setGameEndTimestamp(sdf.format(endTimeStmpa));
			
			JSONArray participantsJsonArr = matchInfoJson.getJSONArray("participants");
			
			JSONObject participantsJson;
			for(int j = 0; j < participantsJsonArr.length(); j++) {
				participantsJson = participantsJsonArr.getJSONObject(j);
				if(participantsJson.get("summonerName").equals(username)) {
					riotMatch.setAssists(participantsJson.get("assists").toString());
					riotMatch.setKill(participantsJson.get("kills").toString());
					riotMatch.setDeaths(participantsJson.get("deaths").toString());
					riotMatch.setChampionName(participantsJson.get("championName").toString());
					
					if(participantsJson.getBoolean("win")) {
						riotMatch.setWin("승리");
					}else{
						riotMatch.setWin("패배");
					}
					
				}
			}
			matchs.add(riotMatch);
		}
		
		matchs.sort(riotMatch);	
		
		riotInfo.setMatchs(matchs);
		
		String matchResult = "";
		for(RiotMatch a : matchs) {
			matchResult = "<1>" + a.toString() + "<br/>" ;
		}
	
		return riotInfo;
	}
}
