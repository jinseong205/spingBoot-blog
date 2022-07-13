package com.jinseong.blog.model;

import lombok.Data;

@Data
public class RiotMatch implements Comparable<RiotMatch> {
	@Override
	public int compareTo(RiotMatch o) {
		double thisStrtTime = Double.parseDouble(getGameStartTimestamp().substring(2).replaceAll("[^0-9]", ""));
		double anotherStrtTime = Double.parseDouble(o.getGameStartTimestamp().substring(2).replaceAll("[^0-9]", ""));

		if (thisStrtTime < anotherStrtTime) {
			return 1;
		} else {
			return -1;
		}

	}

	private String gameMode;
	private String gameStartTimestamp;
	private String gameEndTimestamp;

	private String championName;
	private String kills;
	private String assists;
	private String deaths;
	private String win;

	@Override
	public String toString() {
		return "RiotMatch [gameMode=" + gameMode + ", gameStartTimestamp=" + gameStartTimestamp + ", gameEndTimestamp="
				+ gameEndTimestamp + ", championName=" + championName + ", kills=" + kills + ", assists=" + assists
				+ ", deaths=" + deaths + ", win=" + win + "]";
	};

}
