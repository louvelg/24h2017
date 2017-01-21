package com.akelio.codingame.util;

public class UtilPrint {

	public static void printMap(String data, int x, int y) {
		
		if(data.length()!=x*y)
			throw new RuntimeException("Invalid data for map with dim: "+x+" & "+y);
		
		for(int i=0;i<x;i++) {
			System.out.println(data.substring(i*y, i*y+y));
		}
	}
}
