package com.akelio.codingame.client;

import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.bots.BotFactory;

public class ClientMain {
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	public static final String DEFAULT_BOT = "1";
	public static final String DEFAULT_GAME = "1";
	public static final String DEFAULT_PARAMS = "no";
	
	public static final String MODE_CREATE = "create";
	public static final String MODE_JOIN = "join";
	
	

	public static void main(String[] args) throws Exception {
		
		Map<String,String> p = Util.argsToParams(args);
		
		String host = Util.getValue(p, "host", DEFAULT_HOST);
		String mode = Util.getValue(p, "mode", DEFAULT_MODE);
		String botId = Util.getValue(p, "bot", DEFAULT_BOT);
		String game = Util.getValue(p, "game", DEFAULT_GAME);
		String params = Util.getValue(p, "params", DEFAULT_PARAMS);
		
		Bot bot = BotFactory.build(botId);
		Client client = new Client(host,game,params,bot);
		
		if(mode.equals(MODE_CREATE))
			client.createGame();
		else if(mode.equals(MODE_JOIN))
			client.joinGame();
		else throw new RuntimeException("Unknown mode: "+mode);
		
		System.out.println("Bye bye");
	}
}
