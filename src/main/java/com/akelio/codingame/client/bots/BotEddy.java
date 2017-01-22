package com.akelio.codingame.client.bots;

import java.util.Map;
import com.akelio.codingame.client.pathsearch.UtilPathSearch;
import com.akelio.codingame.util.UtilBot;
import com.akelio.codingame.util.UtilEngine;

public class BotEddy implements Bot {

	public DIR computeDirection(Map data) {
		Map turn = (Map) data.get("lastTurn");
		Map map = (Map) data.get("map");
		String me = (String) data.get("currentBot");
		String d = (String) turn.get("data");
		
		int nbSomme = Integer.valueOf("" + map.get("nbSomme"));
		int height = Integer.valueOf("" + map.get("height"));
		int width = Integer.valueOf("" + map.get("width"));
		
		int meIndex = d.indexOf(me);
		
		int[] amounts = UtilBot.findSommeValues(nbSomme, width, height, d);
		int[] mePos = UtilEngine.convert1Dto2D(width, height, meIndex);
		int[][] sommes = UtilBot.findSommes(nbSomme,width,height,d);
		boolean[][] maze = UtilBot.buildMaze(width,height,d);
		
		int bestIndex = maxAmountIndex(amounts);
		int[] bestPos = sommes[bestIndex];
		
		int[][] path = UtilPathSearch.perform(mePos, bestPos, maze);
		int[] nextPos = path[1];
		
		return UtilBot.findNextDir(mePos, nextPos);
	}

	
	private int maxAmountIndex(int[] amounts) {
		int max = 0;
		int index = -1; 
		
		for(int i =0; i < amounts.length; i++) {
			 if(amounts[i] > max) {
				 max = amounts[i];
				 index = i;
			 }
		 }
		
		return index;
	}
	
	public String getId() {
		return "botEddy";
	}

}
