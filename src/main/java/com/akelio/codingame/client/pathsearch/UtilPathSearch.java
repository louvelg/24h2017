package com.akelio.codingame.client.pathsearch;

public class UtilPathSearch {

	public static int[][] perform(int[] start, int[] end, boolean[][] maze) {
		Search search = new Search();
		search.setStart(start);
		search.setEnd(end);
		search.initMaze(maze);
		search.run();
		
		return search.getPath();
	}
	
	public static int[][] perform(int[] start, int[][] ends, boolean[][] maze) {
		SearchMany search = new SearchMany();
		search.setStart(start);
		search.setEnds(ends);
		search.initMaze(maze);
		search.run();
		
		return search.getPath();
	}

}
