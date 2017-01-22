package com.akelio.codingame.client.bots;

import java.util.Map;

public class Bot1 implements Bot {

	public DIR computeDirection(Map data) {
		int i = (int) (Math.random()*4);
		if ( i == 0 ) return DIR.N;
		if ( i == 1 ) return DIR.E;
		if ( i == 2 ) return DIR.W;
		if ( i == 3 ) return DIR.S;
		return DIR.I;
	}

	
	public String getId() {
		return "1";
	}

}
