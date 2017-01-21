package com.akelio.codingame.util;

import java.util.Date;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.turn.entity.Turn;

public class UtilEngine {
	
	
	public static Turn computeNextTurn(Game game, String indice, Move[] moves) {
		
		Turn previous = game.getLastTurn();
		int h = 0;
		int w = 0;
		String data = previous.getData();
		
		String directionA = moves[0].getDirection();
		int posA = data.indexOf("A");
		int newPosA = computeNewPos(w,h,posA,directionA);
		
		String directionB = moves[1].getDirection();
		String directionC = moves[2].getDirection();
		String directionD = moves[3].getDirection();
		
		int posB = data.indexOf("B");
		int posC = data.indexOf("C");
		int posD = data.indexOf("D");
		
		Turn turn = new Turn();
		turn.setIndice(indice);
		turn.setGameId(game.getGameId());
		turn.setDateCreated(new Date());
		
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		
		turn.setData(previous.getData());
		
		return turn;
	}
	
	
	private static int computeNewPos(int w, int h, int pos, String direction) {
		int[] pos2 = convert1Dto2D(w,h,pos);
		if(direction.equals("N")) pos2[0]--;
		if(direction.equals("S")) pos2[0]++;
		if(direction.equals("W")) pos2[1]--;
		if(direction.equals("E")) pos2[1]++;
		return convert2Dto1D(w, h, pos2[0], pos2[1]);
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
