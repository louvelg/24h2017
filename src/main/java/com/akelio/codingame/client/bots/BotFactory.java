package com.akelio.codingame.client.bots;

public class BotFactory {

	public static Bot build(String id) {
		if(id.equals("1")) return new Bot1();
		if(id.equals("2")) return new Bot2();
		if(id.equals("3")) return new Bot3();
		if(id.equals("eddy")) return new BotEddy();
		
		throw new RuntimeException("Unknown bot id: "+id);
	}
}
