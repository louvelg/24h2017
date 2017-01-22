package com.akelio.codingame.client;

import java.awt.Toolkit;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.json.UtilJson;

public class Client {
	
	public static final String PATH_CREATE = "/rest/v1/newgame/{params}";
	public static final String PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
	
	public static final String PATH_GAME = "/rest/v1/bot/{indice}/game/{gameId}/move/{move}";
	
	private String host;
	private String game;
	private String params;
	private Bot bot;
	
	public Client(String host, String game, String params, Bot bot) {
		this.host = host;
		this.game = game;
		this.params = params;
		this.bot = bot;
	}

	
	public int createGame() throws Exception {
		
		URL url = new URL(host+PATH_CREATE.replace("{params}", params));
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
		Map turn = (Map) data.get("lastTurn");
		
		String status = (String) data.get("status");
		String currentBot = (String) data.get("currentBot");
		String gameId = (String) data.get("gameId");
		String maxTurn = (String) data.get("maxTurn");
		String indexTurn = (String) turn.get("indice");
		
		boolean over = false;
		
		if(status!=null && status.equals("timeout")) {
			System.out.println();
			System.out.println("GAME ABORTED : no enough player ....");
			return;
		}

		System.out.println();
		System.out.println("GAME JOINED");
		System.out.println(data);
		
		while (!over) {
			
			try {
				Bot.DIR dir = bot.computeDirection(data);
				
				String urlGame_ = host+PATH_GAME
						.replace("{indice}", currentBot)
						.replace("{gameId}", gameId)
						.replace("{move}", dir.toString());
				
				System.out.println(indexTurn+"/"+maxTurn+" : " + data.get("currentBot") + " game-url " + urlGame_);
				
				URL urlGame = new URL(urlGame_);
				con = (URLConnection) urlGame.openConnection();
				
				res = Util.retrieveString(con);
				data = (Map) UtilJson.parseJson(res);
				turn = (Map) data.get("lastTurn");
				
				indexTurn = (String) turn.get("indice");
				status = (String) data.get("status");
				over = status.equals("over");
				
				Thread.sleep(100);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Game status: "+status);
		Toolkit.getDefaultToolkit().beep();
	}


	
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}
}
