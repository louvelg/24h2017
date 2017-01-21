package com.akelio.codingame.util;

import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.turn.entity.Turn;

public class UtilEngine {
	
	
	public static Turn computeNextTurn(Game game, String indice, Move[] moves) {
		
		Turn previous = game.getLastTurn();
		
		Turn turn = new Turn();
		turn.setIndice(indice);
		turn.setGameId(game.getGameId());
		
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		turn.setData(previous.getData());
		
		return turn;
	}
	

	public static int[] convert1Dto2D(int w, int h, int pos) {
		return new int[]{pos/w,pos%h};
	}
	
	public static int convert2Dto1D(int w, int h, int x, int y) {
		return w*x+y;
	}
	
	public static int random(int limit) {
		return (int) (Math.random()*limit);
	}
}
