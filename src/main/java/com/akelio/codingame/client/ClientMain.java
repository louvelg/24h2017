package com.akelio.codingame.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import com.akelio.codingame.client.json.UtilJson;

public class ClientMain {
	
	public static final String PATH_CREATE = "/rest/v1/game";
	public static final String PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	
	public static final String MODE_CREATE = "create";
	public static final String MODE_JOIN = "join";
	
	

	public static void main(String[] args) throws Exception {
		
		Map<String,String> params = Util.argsToParams(args);
		
		String host = Util.getValue(params, "host", DEFAULT_HOST);
		String mode = Util.getValue(params, "mode", DEFAULT_MODE);
		String bot = Util.getValue(params, "bot", null);
		String game = Util.getValue(params, "game", null);
		
		if(mode.equals(MODE_CREATE))
			createGame(host);
		else if(mode.equals(MODE_JOIN))
			joinGame(host,bot,game);
		else throw new RuntimeException("Unknown mode: "+mode);
		
		System.out.println("Bye bye");
	}
	
	
	
	private static void createGame(String host) throws Exception {
		
		URL url = new URL(host+PATH_CREATE);
		URLConnection con = (URLConnection) url.openConnection();
		String res = Util.retrieveString(con);
		System.out.println("res="+res);
		
		Map data = (Map) UtilJson.parseJson(res);
		System.out.println(data);
	}
	
	
	
	private static void joinGame(String host, String bot, String game) throws IOException {
		
		String url_ = host+PATH_JOIN.replace("{botId}",bot).replace("{gameId}",game);
		URL url = new URL(url_);
		
		URLConnection con = (URLConnection) url.openConnection();
		String res = Util.retrieveString(con);
		
		System.out.println(res);
	}
}
