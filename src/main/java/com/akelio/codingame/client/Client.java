package com.akelio.codingame.client;

import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.akelio.codingame.client.json.UtilJson;

public class Client {
	
	public static final String PATH_CREATE = "/rest/v1/game";
	public static final String PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
	
	public static final String PATH_GAME = "/rest/v1/bot/{indice}/game/{gameId}/move/{move}";
	
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
		System.out.println(data);
		
		String urlGame_ = host+PATH_GAME
				.replace("{indice}", (String) data.get("currentBot"))
				.replace("{gameId}", (String) data.get("gameId"))
				.replace("{move}", (String) getCoordinate());
		System.out.println(urlGame_);
		URL urlGame = new URL(urlGame_);
		URLConnection conGame = (URLConnection) urlGame.openConnection();
		String resGame = Util.retrieveString(conGame);
		Map dataGame = (Map) UtilJson.parseJson(resGame);
		
		System.out.println("dataGame :: " + dataGame);
		
		for (int i = 0; i < 10; i++) {
			String urlGame2_ = host+PATH_GAME
					.replace("{indice}", (String) data.get("currentBot"))
					.replace("{gameId}", (String) data.get("gameId"))
					.replace("{move}", (String) getCoordinate());
			System.out.println("urlGame2_ :: " + urlGame2_);
			URL urlGame2 = new URL(urlGame2_);
			URLConnection conGame2 = (URLConnection) urlGame2.openConnection();
			String resGame2 = Util.retrieveString(conGame2);
			Map dataGame2 = (Map) UtilJson.parseJson(resGame2);
			System.out.println(dataGame2);
		}
	}
	
	
	private String getCoordinate() {
		int i = (int) ((Math.random())*3);
		if ( i == 0 ) return "N";
		if ( i == 1 ) return "E";
		if ( i == 2 ) return "W";
		if ( i == 3 ) return "S";
		return "";
	}
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}
}
