package com.akelio.codingame.client;

public class ClientMultiMain {
	
public static void main(String[] args) throws Exception {
		ClientMain.main(new String[]{"mode=create"});
		ClientMain.main(new String[]{"mode=join"});
		ClientMain.main(new String[]{"mode=join"});
		ClientMain.main(new String[]{"mode=join"});
		ClientMain.main(new String[]{"mode=join"});
	}

}
