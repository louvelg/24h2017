package com.akelio.codingame.util;

public class UtilRandom {

	
	public static int random(int limit) {
		return (int) (Math.random()*limit);
	}
	
	public static int random(int limit1, int limit2) {
		return limit1 + random(limit2-limit1);
	}
	
	public static boolean chance(int limit) {
		return random(limit)==0;
	}
}
