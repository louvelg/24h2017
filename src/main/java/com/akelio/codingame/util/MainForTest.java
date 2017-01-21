package com.akelio.codingame.util;

import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.map.entity.Map;
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
		
		UtilPrint.printDataMap(data, 50, 50);
	}
	
	
	
	
	
	private static void testEngine() {
		int w = 50;
		int h = 50;
		
		char[] sommes = new char[]{'1','2','3','4','5','6'};
		char[] bots = new char[]{'A','B','C','D'};
		
		String data = UtilMaze.buildMaze(w,h);
		data = UtilMaze.addCells(data,bots);
		data = UtilMaze.addCells(data,sommes);
		
		UtilPrint.printDataMap(data, w, h);
		
		Map map = new Map();
		
		map.setMaxSomme("1");
		map.setMinSomme("9");
		map.setNbSomme(""+sommes.length);
		map.setData(data);
		map.setHeight(""+h);
		map.setWidth(""+w);
		
		Turn turn = new Turn();
		turn.setData(data);
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		
		Game game = new Game();
		game.setMap(map);
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
		UtilPrint.printDataMap(nextTurn.getData(), w, h);
	}

}
