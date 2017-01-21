package com.akelio.codingame.util;

public class UtilMazeMain {

	public static void main(String[] args) {
		String data = UtilMaze.buildMaze(50, 50);
		data = UtilMaze.addCells(data,new char[]{'A','B','C','D'});
		data = UtilMaze.addCells(data,new char[]{'1','2','3','4','5','6'});
		
		UtilPrint.printMap(data, 50, 50);
	}

}
