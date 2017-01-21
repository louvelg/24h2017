package com.akelio.codingame.util;

public class MainForTest {

	public static void main(String[] args) {
//		genMaze();
		test();
	}
	
	private static void genMaze() {
		String data = UtilMaze.buildMaze(50, 50);
		data = UtilMaze.addCells(data,new char[]{'A','B','C','D'});
		data = UtilMaze.addCells(data,new char[]{'1','2','3','4','5','6'});
		
		UtilPrint.printMap(data, 50, 50);
	}
	
	private static void test() {
		int[] pos = UtilEngine.convert1Dto2D(10,10,53);
		System.out.println("53 -> "+pos[0]+" "+pos[1]);

		int pos1 = UtilEngine.convert2Dto1D(10, 10, 5, 5);
		System.out.println("5 5 -> "+pos1);
	}

}
