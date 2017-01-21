package com.akelio.codingame.util;

import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.turn.entity.Turn;

public class MainForTest {

	public static void main(String[] args) {
//		genMaze();
//		test1();
		testEngine();
	}
	
	private static void genMaze() {
		String data = UtilMaze.buildMaze(50, 50);
		data = UtilMaze.addCells(data,new char[]{'A','B','C','D'});
		data = UtilMaze.addCells(data,new char[]{'1','2','3','4','5','6'});
		
		UtilPrint.printMap(data, 50, 50);
	}
	
	private static void test1() {
		int[] pos = UtilEngine.convert1Dto2D(10,10,53);
		System.out.println("53 -> "+pos[0]+" "+pos[1]);

		int pos1 = UtilEngine.convert2Dto1D(10, 10, 5, 5);
		System.out.println("5 5 -> "+pos1);
	}
	
	
	
	private static void testEngine() {
		String data = UtilMaze.buildMaze(50, 50);
		data = UtilMaze.addCells(data,new char[]{'A','B','C','D'});
		data = UtilMaze.addCells(data,new char[]{'1','2','3','4','5','6'});
		
		UtilPrint.printMap(data, 50, 50);
		
		Turn turn = new Turn();
		turn.setData(data);
		
		Game game = new Game();
		game.getTurnList().add(turn);
		
		Move m1 = new Move();
		m1.setDirection("N");
		Move m2 = new Move();
		m2.setDirection("S");
		Move m3 = new Move();
		m3.setDirection("E");
		Move m4 = new Move();
		m4.setDirection("W");
		
		Turn nextTurn = UtilEngine.computeNextTurn(game, "1", new Move[]{m1,m2,m3,m4});
		UtilPrint.printMap(nextTurn.getData(), 50, 50);
	}

}
