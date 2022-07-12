package com.jinseong.blog.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;

import lombok.Data;

@Data
public class RiotMatch implements Comparator<RiotMatch>{
	private String gameMode; 
	private String gameStartTimestamp;
	private String gameEndTimestamp;
	
	
	private String championName;
	private String kill;
	private String assists;
	private String deaths;
	private String win;
	
	public int compare(RiotMatch o1, RiotMatch o2) {
		double o1strtTime = Double.parseDouble(o1.getGameStartTimestamp().substring(2).replaceAll("[^0-9]", ""));
		double o2strtTime =  Double.parseDouble(o2.getGameStartTimestamp().substring(2).replaceAll("[^0-9]", ""));;
		
		if(o1strtTime < o2strtTime) {
			return 1;
		}else {
			return -1;
		}
		
	}

	@Override
	public String toString() {
		return "RiotMatch [gameMode=" + gameMode + ", gameStartTimestamp=" + gameStartTimestamp + ", gameEndTimestamp="
				+ gameEndTimestamp + ", championName=" + championName + ", kill=" + kill + ", assists=" + assists
				+ ", deaths=" + deaths + ", win=" + win + "]";
	};
	
	

	
}
