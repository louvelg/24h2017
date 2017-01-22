package com.akelio.codingame.client.bots;

public class BotFactory {

	public static Bot build(String id) {
		if(id.equals("1")) return new Bot1();
		if(id.equals("2")) return new Bot2();
		
		throw new RuntimeException("Unknown bot id: "+id);
	}
}
