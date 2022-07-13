package com.jinseong.blog.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

		/* Summoner */
		riotInfo.setId("xn8OuLULnBPa7kfTh2ZZqUx6gSU6CLoSBko_QQRsWBfozoHcWFLOWWvmgQ");
		riotInfo.setPuuid("9ZP5_tPfWVNKHgZFHaxwdoAqiZR47kQaAWDR5fCntho1eW6ELIBLwaCYwJ3r_i2YfOs4gdZiNih5WQ");
		riotInfo.setName("고마우미");
		riotInfo.setSummonerLevel("38");

		/* League Entry */
		riotInfo.setTier("SILVER");
		riotInfo.setRank("IV");
		riotInfo.setLeaguePoints("40");
		riotInfo.setWins("14");
		riotInfo.setLosses("18");

		/* match By puuuid */
		List<RiotMatch> matchs = new ArrayList<>();
		RiotMatch[] riotMatch = new RiotMatch[5];

		riotMatch[0] = new RiotMatch();
		riotMatch[0].setGameMode("CLASSIC");
		riotMatch[0].setGameStartTimestamp("2022/07/13 16:00");
		riotMatch[0].setGameEndTimestamp("2022/07/13 16:39");
		riotMatch[0].setChampionName("LeeSin");
		riotMatch[0].setKills("4");
		riotMatch[0].setDeaths("10");
		riotMatch[0].setAssists("19");
		riotMatch[0].setWin("승리");

		riotMatch[1] = new RiotMatch();
		riotMatch[1].setGameMode("CLASSIC");
		riotMatch[1].setGameStartTimestamp("2022/07/13 15:10");
		riotMatch[1].setGameEndTimestamp("2022/07/13 15:31");
		riotMatch[1].setChampionName("LeeSin");
		riotMatch[1].setKills("6");
		riotMatch[1].setDeaths("3");
		riotMatch[1].setAssists("13");
		riotMatch[1].setWin("승리");

		riotMatch[2] = new RiotMatch();
		riotMatch[2].setGameMode("ARAM");
		riotMatch[2].setGameStartTimestamp("2022/07/12 17:18");
		riotMatch[2].setGameEndTimestamp("2022/07/12 17:39");
		riotMatch[2].setChampionName("Jhin");
		riotMatch[2].setKills("14");
		riotMatch[2].setDeaths("5");
		riotMatch[2].setAssists("15");
		riotMatch[2].setWin("패배");

		riotMatch[3] = new RiotMatch();
		riotMatch[3].setGameMode("ARAM");
		riotMatch[3].setGameStartTimestamp("2022/07/12 16:23");
		riotMatch[3].setGameEndTimestamp("2022/07/12 16:41");
		riotMatch[3].setChampionName("Caitlyn");
		riotMatch[3].setKills("6");
		riotMatch[3].setDeaths("9");
		riotMatch[3].setAssists("11");
		riotMatch[3].setWin("패배");

		riotMatch[4] = new RiotMatch();
		riotMatch[4].setGameMode("ARAM");
		riotMatch[4].setGameStartTimestamp("22022/07/12 10:59");
		riotMatch[4].setGameEndTimestamp("2022/07/12 11:12");
		riotMatch[4].setChampionName("Ashe");
		riotMatch[4].setKills("5");
		riotMatch[4].setDeaths("5");
		riotMatch[4].setAssists("26");
		riotMatch[4].setWin("승리");

		for (RiotMatch rm : riotMatch) {
			matchs.add(rm);
		}

		Collections.sort(matchs);
		riotInfo.setMatchs(matchs);

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

		String[] matchIds = null;
		if (monoString.block() != null && !monoString.block().replaceAll(" ", "").equals("[]")) {
			matchIds = monoString.block().split(",");
		}
		/* match By matchId */
		String matchByMatchIdReqUrl;
		String matchId;

		List<RiotMatch> matchs = new ArrayList<RiotMatch>();

		RiotMatch riotMatch = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

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

			Timestamp stratTimeStamp = null;
			Timestamp endTimeStamp = null;

			try {
				stratTimeStamp = new Timestamp(Long.parseLong(matchInfoJson.get("gameStartTimestamp").toString()));
				endTimeStamp = new Timestamp(Long.parseLong(matchInfoJson.get("gameEndTimestamp").toString()));
				riotMatch.setGameStartTimestamp(sdf.format(stratTimeStamp));
				riotMatch.setGameEndTimestamp(sdf.format(endTimeStamp));
			} catch (Exception e) {
			}

			riotMatch.setGameMode(matchInfoJson.getString("gameMode"));

			JSONArray participantsJsonArr = matchInfoJson.getJSONArray("participants");

			JSONObject participantsJson;
			for (int j = 0; j < participantsJsonArr.length(); j++) {
				participantsJson = participantsJsonArr.getJSONObject(j);
				if (participantsJson.get("summonerName").equals(username)) {
					riotMatch.setAssists(participantsJson.get("assists").toString());
					riotMatch.setKills(participantsJson.get("kills").toString());
					riotMatch.setDeaths(participantsJson.get("deaths").toString());
					riotMatch.setChampionName(participantsJson.get("championName").toString());

					if (participantsJson.getBoolean("win")) {
						riotMatch.setWin("승리");
					} else {
						riotMatch.setWin("패배");
					}

				}
			}
			matchs.add(riotMatch);
		}

		try {
			Collections.sort(matchs);
		} catch (Exception e) {
		}
		riotInfo.setMatchs(matchs);
		return riotInfo;
	}
}
