package com.akelio.codingame.util;

public class UtilCell {

	
	public static char buildCell(boolean space) {
		return space?' ':'+';
	}
	
	public static String addCells(String data, char[] values) {
		
		int valuesNb = values.length;
		if(valuesNb==0) return data;
		
		int left = countLeft(data);
		if(left<valuesNb) throw new RuntimeException("Invalid data: "+data);
		
		int[] pos = new int[valuesNb];
		for(int i=0;i<valuesNb;i++) {
			pos[i] = UtilRandom.random(left-i);
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
}
