package com.akelio.codingame.client.bots;

import java.util.Map;

public class Bot2 implements Bot {

	public DIR computeDirection(Map data) {
		Map turn = (Map) data.get("lastTurn");
		String s = (String) turn.get("data");
		String me = (String) data.get("currentBot");
		
		return DIR.I;
	}

	
	public String getId() {
		return "2";
	}

}
