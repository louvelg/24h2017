package com.akelio.codingame.util;

import java.util.ArrayList;
import java.util.Collections;

public class UtilMaze {
	
	public static final double MUTATION = 0.05;
	
	public static String buildMaze(int w, int h) {
		
		int r = UtilRandom.random(6);
		return buildMaze(w,h,r);
	}
	
	public static String buildMaze(int w, int h, int r) {
		
		switch(r) {
			case 0:return buildMaze1(w,h);
			case 1:return buildMaze2(w,h,3);
			case 2:return buildMaze2(w,h,5);
			case 3:return buildMaze2(w,h,8);
			case 4:return buildMaze2(w,h,15);
			case 5:return buildMaze3(w,h); 
		}
		
		return buildMaze3(w,h);
	}
	
	
	
	public static String buildMaze1(int w, int h) {
		System.out.println("building maze type 1");
		
		boolean[][] maze = generate(w,h);
		StringBuffer b = new StringBuffer();
		
		for (int i = 0; i < w; i++)
		for (int j = 0; j < h; j++) {
			char cell = UtilCell.buildCell(maze[i][j]);
			b.append(cell);
		}
		return b.toString();
	}
	
	public static String buildMaze2(int w, int h, int rate) {
		System.out.println("building maze type 2 with rate="+rate);
		
		StringBuffer b = new StringBuffer();
		int len = w*h;
		for (int i=0;i<len;i++) {
			boolean isWall = UtilRandom.chance(rate);
			char cell = UtilCell.buildCell(!isWall);
			b.append(cell);
		}

		return b.toString();
	}
	
	public static String buildMaze3(int w, int h) {
		System.out.println("building maze type 3 (empty)");
		
		StringBuffer b = new StringBuffer();
		int len = w*h;
		for (int i=0;i<len;i++) {
			char cell = UtilCell.buildCell(true);
			b.append(cell);
		}

		return b.toString();
	}
	
	
	
	

	private static boolean[][] generate(int w, int h)
	{
		boolean[][] data = new boolean[w][h];
		
		for(int i=0;i<w;i++)
		for(int j=0;j<h;j++)
		data[i][j] = false;
		
		perform(data,1,1);
		return data;
	}
	
	
	private static void perform(boolean[][] data, int x, int y)
	{
		data[x][y] = true;
		ArrayList<Integer> dirs = randomDirections();
		for(int dir:dirs) switch(dir)
		{
			case 1:goto_north(data,x,y);break;
			case 2:goto_south(data,x,y);break;
			case 3:goto_west(data,x,y);break;
			case 4:goto_east(data,x,y);break;
			default:break;
		}
	}
	
	
	private static ArrayList<Integer> randomDirections()
	{
		ArrayList<Integer> dirs = new ArrayList<>();
		for(int i=1;i<=4;i++) dirs.add(i);
		Collections.shuffle(dirs);
		return dirs;
	}
	
	
	private static boolean epsilon()
	{return Math.random()<MUTATION;}
	
	
	
	private static void goto_north(boolean[][] data, int x, int y)
	{
		if(y<2) return;
		if(!epsilon() && (data[x][y-1] || data[x][y-2])) return;
		data[x][y-1] = true;
		perform(data,x,y-2);
	}
	
	private static void goto_south(boolean[][] data, int x, int y)
	{
		if(y>data[0].length-3) return;
		if(!epsilon() && (data[x][y+1] || data[x][y+2])) return;
		data[x][y+1] = true;
		perform(data,x,y+2);
	}
	
	private static void goto_west(boolean[][] data, int x, int y)
	{
		if(x<2) return;
		if(!epsilon() && (data[x-1][y] || data[x-2][y])) return;
		data[x-1][y] = true;
		perform(data,x-2,y);
	}
	
	private static void goto_east(boolean[][] data, int x, int y)
	{
		if(x>data.length-3) return;
		if(!epsilon() && (data[x+1][y] || data[x+2][y])) return;
		data[x+1][y] = true;
		perform(data,x+2,y);
	}
}
