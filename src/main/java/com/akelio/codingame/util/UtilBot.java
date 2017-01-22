package com.akelio.codingame.util;

import com.akelio.codingame.client.bots.Bot;
import com.akelio.codingame.client.bots.Bot.DIR;

public class UtilBot {

	public static Bot.DIR findNextDir(int[] start, int[] end) {
		if (start[0] == end[0])
			return start[1] > end[1] ? DIR.W : DIR.E;
		return start[0] > end[0] ? DIR.N : DIR.S;
	}
	
	public static boolean[][] buildMaze(int w, int h, String d) {
		boolean[][] maze = new boolean[w][h];
		for (int i = 0; i < w; i++)
		for (int j = 0; j < h; j++) {
			int pos = UtilEngine.convert2Dto1D(w, h, i, j);
			char cell = d.charAt(pos);
			maze[i][j] = cell != '+';
		}
		return maze;
	}
	
	public static int[][] findSommes(int nb, int w, int h, String d) {
		int[][] sommes = new int[nb][2];
		int k = 0;
		for (int i = 0; i < d.length(); i++) {
			char c = d.charAt(i);
			if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
				sommes[k] = UtilEngine.convert1Dto2D(w, h, i);
				k++;
			}
		}
		return sommes;
	}
	
	
	public static int[] findSommeValues(int nb, int w, int h, String d) {
		int[] values = new int[nb];
		int k = 0;
		for (int i = 0; i < d.length(); i++) {
			char c = d.charAt(i);
			if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
				values[k] = Integer.parseInt(""+c);
				k++;
			}
		}
		return values;
	}
}
