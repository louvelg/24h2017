package com.akelio.codingame.util;

public class UtilPrint {

	public static void printDataMap(String data, int w, int h) {
		
		if(data.length()!=w*h)
			throw new RuntimeException("Invalid data for map with dim: "+w+" & "+h);
		
		for(int i=0;i<h;i++) {
			System.out.println(data.substring(i*w, i*w+w));
		}
	}
}
