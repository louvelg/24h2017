package com.akelio.codingame.client;

import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.bots.BotFactory;

public class ClientMain {
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	public static final String DEFAULT_BOT = "1";
	public static final String DEFAULT_GAME = "1";
	
	public static final String MODE_CREATE = "create";
	public static final String MODE_JOIN = "join";
	
	

	public static void main(String[] args) throws Exception {
		
		Map<String,String> params = Util.argsToParams(args);
		
		String host = Util.getValue(params, "host", DEFAULT_HOST);
		String mode = Util.getValue(params, "mode", DEFAULT_MODE);
		String botId = Util.getValue(params, "bot", DEFAULT_BOT);
		String game = Util.getValue(params, "game", DEFAULT_GAME);
		
		Bot bot = BotFactory.build(botId);
		Client client = new Client(host,game,bot);
		
		if(mode.equals(MODE_CREATE))
			client.createGame();
		else if(mode.equals(MODE_JOIN))
			client.joinGame();
		else throw new RuntimeException("Unknown mode: "+mode);
		
		System.out.println("Bye bye");
	}
}
