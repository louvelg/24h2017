package com.akelio.codingame.client;

import java.util.Map;
import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.bots.BotFactory;

public class ClientMultiMain {
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	public static final String DEFAULT_BOT = "1";
	public static final String DEFAULT_GAME = "1";
	public static final String DEFAULT_PARAMS = "no";
	
	
	public static void main(String[] args) throws Exception {
		
		Map<String,String> p = Util.argsToParams(args);
		
//		String mode = Util.getValue(p, "mode", DEFAULT_MODE);
//		String gameId = Util.getValue(p, "game", DEFAULT_GAME);
//		String botId = Util.getValue(p, "bot", DEFAULT_BOT);
		
		String host = Util.getValue(p, "host", DEFAULT_HOST);
		String params = Util.getValue(p, "params", DEFAULT_PARAMS);
		
		params = "20-20-5-1-9-6";
		//height-width-mapType-minSomme-maxSomme-nbSomme
		
		//type 0 : labyrithme
		//type 1 : blocs aléatoires (dense)
		//type 2 : blocs aléatoires (moyen)
		//type 3 : blocs aléatoires (parsemé)
		//type 4 : blocs aléatoires (très parsemé)
		//type 5 : pas de blocs (map vide)
		
		Bot bot1 = BotFactory.build("2");
		Bot bot2 = BotFactory.build("2");
		Bot bot3 = BotFactory.build("2");
		Bot bot4 = BotFactory.build("3");
		
		Client client0 = new Client(host,null,params,bot1);
		String gameId = ""+client0.createGame();
		
		Client client1 = new Client(host,gameId,params,bot1);
		Client client2 = new Client(host,gameId,params,bot2);
		Client client3 = new Client(host,gameId,params,bot3);
		Client client4 = new Client(host,gameId,params,bot4);
		
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
