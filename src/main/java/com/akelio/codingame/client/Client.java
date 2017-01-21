package com.akelio.codingame.client;

import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.akelio.codingame.client.json.UtilJson;

public class Client {
	
	public static final String PATH_CREATE = "/rest/v1/game";
	public static final String PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
	
	
	
	private String host;
	private String bot;
	private String game;
	
	public Client(String host, String bot, String game) {
		this.host = host;
		this.bot = bot;
		this.game = game;
	}

	
	public int createGame() throws Exception {
		
		URL url = new URL(host+PATH_CREATE);
		URLConnection con = (URLConnection) url.openConnection();
		String res = Util.retrieveString(con);
		Map data = (Map) UtilJson.parseJson(res);
		
		System.out.println();
		System.out.println("GAME CREATION");
		System.out.println(data);
		
		return toInt((String) data.get("gameId"));
	}
	
	
	
	public void joinGame() throws Exception {
		
		String url_ = host+PATH_JOIN.replace("{botId}",bot).replace("{gameId}",game);
		URL url = new URL(url_);
		
		URLConnection con = (URLConnection) url.openConnection();
		String res = Util.retrieveString(con);
		Map data = (Map) UtilJson.parseJson(res);

		System.out.println();
		System.out.println("GAME JOINED");
		System.out.println("res="+res);
		System.out.println(data);
	}
	
	
	
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}
}
