package com.akelio.codingame.client;

import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.bots.BotFactory;

public class ClientMultiMain {
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	public static final String DEFAULT_BOT = "1";
	public static final String DEFAULT_GAME = "1";
	
	
	public static void main(String[] args) throws Exception {
		
		Map<String,String> params = Util.argsToParams(args);
		
		String host = Util.getValue(params, "host", DEFAULT_HOST);
		String mode = Util.getValue(params, "mode", DEFAULT_MODE);
		String botId = Util.getValue(params, "bot", DEFAULT_BOT);
		String game = Util.getValue(params, "game", DEFAULT_GAME);
		
		
		Bot bot1 = BotFactory.build("1");
		Bot bot2 = BotFactory.build("1");
		Bot bot3 = BotFactory.build("2");
		Bot bot4 = BotFactory.build("2");
		
		Client client0 = new Client(host,null,bot1);
		String gameId = ""+client0.createGame();
		
		Client client1 = new Client(host,gameId,bot1);
		Client client2 = new Client(host,gameId,bot2);
		Client client3 = new Client(host,gameId,bot3);
		Client client4 = new Client(host,gameId,bot4);
		
		launch(client1);
		launch(client2);
		launch(client3);
		launch(client4);
	}
	
	
	private static void launch(final Client client) {
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					client.joinGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		});
		
		t.start();
	}

}
