package com.akelio.codingame.client.bots;

import java.util.Map;
import com.akelio.codingame.client.pathsearch.UtilPathSearch;
import com.akelio.codingame.util.UtilEngine;

public class Bot2 implements Bot {

	public DIR computeDirection(Map data) {
		Map turn = (Map) data.get("lastTurn");
		String d = (String) turn.get("data");
		String me = (String) data.get("currentBot");
		int meIndex = d.indexOf(me);
		int nbSomme = Integer.valueOf("" + ((Map) data.get("map")).get("nbSomme"));
		int height = Integer.valueOf("" + ((Map) data.get("map")).get("height"));
		int width = Integer.valueOf("" + ((Map) data.get("map")).get("width"));
		int[] mePos = UtilEngine.convert1Dto2D(width, height, meIndex);

		int[][] sommes = new int[nbSomme][2];
		int k = 0;
		for (int i = 0; i < d.length(); i++) {
			if (d.charAt(i) == '1' || d.charAt(i) == '2' || d.charAt(i) == '3' || d.charAt(i) == '4' || d.charAt(i) == '5' || d.charAt(i) == '6' || d.charAt(i) == '7' || d.charAt(i) == '8'
					|| d.charAt(i) == '9') {
				int[] sommePos = UtilEngine.convert1Dto2D(width, height, i);
				sommes[k] = sommePos;
				k++;
			}
		}
		
		boolean[][] murs = new boolean[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pos = UtilEngine.convert2Dto1D(width, height, i, j);
				char cell = d.charAt(pos);
				murs[i][j] = cell != '+';
			}
		}
		
		int[][] path = UtilPathSearch.perform(mePos, sommes, murs);

		int[] nextPos = path[1];
		
		return getNextDirection(mePos, nextPos);
	}

	
	public DIR getNextDirection(int[] me, int[] next) {
		if (me[0] == next[0]) {
			if (me[1] > next[1]) {
				return DIR.W;
			} else {
				return DIR.E;
			}
		} else {
			if (me[0] > next[0]) {
				return DIR.N;
			} else {
				return DIR.S;
			}
		}
		
	}
	
	public String getId() {
		return "2";
	}

}
