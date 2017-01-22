package com.akelio.codingame.client;

import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.json.UtilJson;

public class Client {
	
	public static final String PATH_CREATE = "/rest/v1/game";
	public static final String PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
	
	public static final String PATH_GAME = "/rest/v1/bot/{indice}/game/{gameId}/move/{move}";
	
	private String host;
	private String game;
	private Bot bot;
	
	public Client(String host, String game, Bot bot) {
		this.host = host;
		this.game = game;
		this.bot = bot;
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
		
		String url_ = host+PATH_JOIN.replace("{botId}",bot.getId()).replace("{gameId}",game);
		URL url = new URL(url_);
		
		URLConnection con = (URLConnection) url.openConnection();
		String res = Util.retrieveString(con);
		Map data = (Map) UtilJson.parseJson(res);
		
		String currentBot = (String) data.get("currentBot");
		String gameId = (String) data.get("gameId");

		System.out.println();
		System.out.println("GAME JOINED");
		System.out.println(data);
		
		boolean over = false;
		
		while (!over) {
			
			Bot.DIR dir = bot.computeDirection(data);
			
			String urlGame_ = host+PATH_GAME
					.replace("{indice}", currentBot)
					.replace("{gameId}", gameId)
					.replace("{move}", dir.toString());
			
			System.out.println("indice : " + data.get("currentBot") + " urlGame = " + urlGame_);
			
			URL urlGame = new URL(urlGame_);
			con = (URLConnection) urlGame.openConnection();
			
			res = Util.retrieveString(con);
			data = (Map) UtilJson.parseJson(res);
			
			String status = (String) data.get("status");
			over = status.equals("over");
			
			Thread.sleep(100);
		}
	}


	
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}
}
