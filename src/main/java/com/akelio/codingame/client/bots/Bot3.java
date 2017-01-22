package com.akelio.codingame.client.bots;

import java.util.Map;
import com.akelio.codingame.client.bots.Bot.DIR;
import com.akelio.codingame.client.pathsearch.UtilPathSearch;
import com.akelio.codingame.util.UtilBot;
import com.akelio.codingame.util.UtilEngine;

public class Bot3 implements Bot {

	public DIR computeDirection(Map data) {
		Map turn = (Map) data.get("lastTurn");
		Map map = (Map) data.get("map");
		String me = (String) data.get("currentBot");
		String d = (String) turn.get("data");
		
		int nbSomme = Integer.valueOf("" + map.get("nbSomme"));
		int height = Integer.valueOf("" + map.get("height"));
		int width = Integer.valueOf("" + map.get("width"));
		
		int meIndex = d.indexOf(me);
		
		int[] mePos = UtilEngine.convert1Dto2D(width, height, meIndex);
		int[][] sommes = UtilBot.findSommes(nbSomme,width,height,d);
		boolean[][] maze = UtilBot.buildMaze(width,height,d);
		
		int[] amounts = UtilBot.findSommeValues(nbSomme, width, height, d);
		sommes = UtilBot.findBestSommes(sommes,amounts);
		
		int[][] path = UtilPathSearch.perform(mePos, sommes, maze);
		if(path==null) return DIR.I;
		
		int[] nextPos = path[1];
		return UtilBot.findNextDir(mePos, nextPos);
	}

	
	
	
	public String getId() {
		return "3";
	}

}
