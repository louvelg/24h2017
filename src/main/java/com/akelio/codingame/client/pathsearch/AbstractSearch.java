package com.akelio.codingame.client.pathsearch;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearch implements Runnable {


    
    private boolean[][] state;
    
    protected int[] start;
    protected int[] current;
    protected int[] end;
    
    protected int maze_x;
    protected int maze_y;
    protected int d_max;
    
    protected int[][] path;
    
    
    
    
    
    public void run()
    {
    	path = null;
    	current = null;
    	
    	if(isFree(end))
    		performSearch();
    }
    
    protected abstract void performSearch();
    
    
    
    public void setStart(int[] start) {
    	this.start = start;
    }
    
    public void setEnd(int[] end) {
    	this.end = end;
    }
    
    
    
    public int[][] getPath() {
    	return path;
    }
    
    public int[] getCurrent() {
    	return current;
    }
    
    public boolean[][] getState() {
    	return state;
    }
    
    
    

    
    
    
    
    protected List<int[]> freeNeighbours(int[] p)
    {
    	int x = p[0];
    	int y = p[1];
    	
    	List<int[]> l = new ArrayList<>();
    	
    	if(isFree(x-1,y)) l.add(p(x-1,y));
    	if(isFree(x+1,y)) l.add(p(x+1,y));
    	if(isFree(x,y-1)) l.add(p(x,y-1));
    	if(isFree(x,y+1)) l.add(p(x,y+1));

    	return l;
    }
    
    
    
    
    public void initMaze(boolean[][] m)
    {
    	maze_x = m.length;
		maze_y = m[0].length;
    	d_max = maze_x + maze_y;
    	
    	state = new boolean[maze_x][maze_y];
    	for(int i=0;i<maze_x;i++) for(int j=0;j<maze_y;j++)
    		state[i][j] = m[i][j];
    }
    
    
    
    
    
    
    

    protected void fill(int[] p)
    {fill(p[0],p[1]);}
    
    
    protected void fill(int x, int y)
    {if(isValid(x,y)) state[x][y] = false;}
    
    
    protected int[] p(int x, int y)
    {return new int[]{x,y};}
    
    
    protected boolean isFree(int[] p)
    {return p!=null && isFree(p[0],p[1]);}
    
    
    protected boolean isFree(int x, int y)
    {return isValid(x,y) && state[x][y];}

    
    protected boolean isValid(int x, int y)
    {return x>=0 && x<maze_x && y>=0 && y<maze_y;}
    
    
    protected int distanceTo(int[] p1, int[] p2)
    {return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);}
    
    
    protected boolean equals(int[] p1, int[] p2)
    {return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}

    
    protected int distanceToEnd(int[] p)
    {return distanceTo(p,end);}
    
    
    protected boolean isEnd(int[] p)
    {return equals(p,end);}
    
    
    protected String toString(int[] p)
    {return p[0]+" "+p[1];}
}

