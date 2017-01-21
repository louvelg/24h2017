package com.akelio.codingame.client;

import java.util.Map;

public class ClientMultiMain {
	
	public static final String DEFAULT_HOST = "http://localhost:8080/codingame";
	public static final String DEFAULT_MODE = "create";
	public static final String DEFAULT_BOT = "1";
	public static final String DEFAULT_GAME = "1";
	
	
	public static void main(String[] args) throws Exception {
		
		Map<String,String> params = Util.argsToParams(args);
		
		String host = Util.getValue(params, "host", DEFAULT_HOST);
		String mode = Util.getValue(params, "mode", DEFAULT_MODE);
		String bot = Util.getValue(params, "bot", DEFAULT_BOT);
		String game = Util.getValue(params, "game", DEFAULT_GAME);
		
		Client client0 = new Client(host,bot,null);
		int gameId = client0.createGame();
		
		Client client1 = new Client(host,bot,""+gameId);
		Client client2 = new Client(host,bot,""+gameId);
		Client client3 = new Client(host,bot,""+gameId);
		Client client4 = new Client(host,bot,""+gameId);
		
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
