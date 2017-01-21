package com.akelio.codingame.util;

import java.util.ArrayList;
import java.util.Collections;

public class UtilMaze {
	
	public static final double MUTATION = 0.05;
	
	
	public static String addCells(String data, char[] values) {
		
		int valuesNb = values.length;
		if(valuesNb==0) return data;
		
		int left = countLeft(data);
		if(left<valuesNb) throw new RuntimeException("Invalid data: "+data);
		
		int[] pos = new int[valuesNb];
		for(int i=0;i<valuesNb;i++) {
			pos[i] = random(left-i);
		}
		for(int i=0;i<valuesNb;i++) {
			data = changeCellAt(data,pos[i],values[i]);
		}
		return data;
	}
	
	
	
	private static String changeCellAt(String data, int pos, char c1) {
		
		int left = 0;
		int len = data.length();
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<len;i++) {
			char c0 = data.charAt(i);
			if(c0==' ') left++;
			
			char c = (c0==' ' && left==pos) ? c1 : c0;
			b.append(c);
		}
		return b.toString();
	}
	
	
	private static int countLeft(String data) {
		int left = 0;
		int len = data.length();
		
		for(int i=0;i<len;i++) {
			if(data.charAt(i)==' ') left++;
		}
		return left;
	}
	
	
	public static String buildMaze(int w, int h) {
		boolean[][] maze = generate(w,h);
		StringBuffer b = new StringBuffer();
		
		for (int i = 0; i < w; i++)
		for (int j = 0; j < h; j++) {
			char cell = buildCell(maze[i][j]);
			b.append(cell);
		}
		return b.toString();
	}
	
	private static char buildCell(boolean space) {
		if(space) return ' ';
		return '+';
	}
	
	private static int random(int limit) {
		return (int) (Math.random()*limit);
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
