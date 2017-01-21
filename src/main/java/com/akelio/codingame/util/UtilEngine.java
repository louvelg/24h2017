package com.akelio.codingame.util;

public class UtilEngine {
	
	public static void computeMoves() {
		
	}
	

	public static int[] convert1Dto2D(int w, int h, int pos) {
		return new int[]{pos/w,pos%h};
	}
	
	public static int convert2Dto1D(int w, int h, int x, int y) {
		return w*x+y;
	}
	
	public static int random(int limit) {
		return (int) (Math.random()*limit);
	}
}
