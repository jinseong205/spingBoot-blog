package com.jinseong.blog.model;


import java.util.List;

import org.json.JSONObject;

import lombok.Data;

@Data
public class RiotInfo {
	//summoner
	private String id;
	private String puuid;
	private String name; // username
	private String summonerLevel; // user level

	//leagueEntriy
	private String tier;
	private String rank;
	private String leaguePoints;
	private String wins;
	private String losses;
	

	private List<JSONObject> matchs;


	@Override
	public String toString() {
		return "RiotInfo [id=" + id + ", puuid=" + puuid + ", name=" + name + ", summonerLevel=" + summonerLevel
				+ ", tier=" + tier + ", rank=" + rank + ", leaguePoints=" + leaguePoints + ", wins=" + wins
				+ ", losses=" + losses + ", matchs=" + matchs + "]";
	}
		



}
