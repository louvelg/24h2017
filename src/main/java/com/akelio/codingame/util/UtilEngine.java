package com.akelio.codingame.util;

import java.util.Date;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.turn.entity.Turn;

public class UtilEngine {
	
	
	public static Turn computeNextTurn(Game game, String indice, Move[] moves) {
		
		Turn previous = game.getLastTurn();
		Map map = game.getMap();
		
		int h = map.getHeightAsInt();
		int w = map.getWidthAsInt();
		int maxSomme = map.getMaxSommeAsInt();
		int minSomme = map.getMinSommeAsInt();
		
		StringBuffer data = new StringBuffer(previous.getData());
		int moneyA = previous.getAmountBot1AsInt();
		int moneyB = previous.getAmountBot2AsInt();
		int moneyC = previous.getAmountBot3AsInt();
		int moneyD = previous.getAmountBot4AsInt();
		
		
		int taken = 0;
		
		String directionA = moves[0].getDirection();
		int posA = data.indexOf("A");
		int newPosA = computeNewPos(w,h,posA,directionA);
		if(newPosA!=-1) {
			char cell = data.charAt(newPosA);
			int gain = getGain(cell);
			if(gain!=-1) {moneyA += gain;taken++;}
			if(cell==' ' || gain!=-1) {
				putCharAt(data,posA,' ');
				putCharAt(data,newPosA,'A');
			}
		}
		
		String directionB = moves[1].getDirection();
		int posB = data.indexOf("B");
		int newPosB = computeNewPos(w,h,posB,directionB);
		if(newPosB!=-1) {
			char cell = data.charAt(newPosB);
			int gain = getGain(cell);
			if(gain!=-1) {moneyB += gain;taken++;}
			if(cell==' ' || gain!=-1) {
				putCharAt(data,posB,' ');
				putCharAt(data,newPosB,'B');
			}
		}
		
		String directionC = moves[2].getDirection();
		int posC = data.indexOf("C");
		int newPosC = computeNewPos(w,h,posC,directionC);
		if(newPosC!=-1) {
			char cell = data.charAt(newPosC);
			int gain = getGain(cell);
			if(gain!=-1) {moneyC += gain;taken++;}
			if(cell==' ' || gain!=-1) {
				putCharAt(data,posC,' ');
				putCharAt(data,newPosC,'C');
			}
		}
		
		String directionD = moves[3].getDirection();
		int posD = data.indexOf("D");
		int newPosD = computeNewPos(w,h,posD,directionD);
		if(newPosD!=-1) {
			char cell = data.charAt(newPosD);
			int gain = getGain(cell);
			if(gain!=-1) {moneyD += gain;taken++;}
			if(cell==' ' || gain!=-1) {
				putCharAt(data,posD,' ');
				putCharAt(data,newPosD,'D');
			}
		}
		
		char[] newTargets = new char[taken];
		for(int i=0;i<taken;i++) {
			int newSomme = UtilRandom.random(minSomme,maxSomme);
			newTargets[i] = (""+newSomme).charAt(0);
		}
		
		String data1 = UtilCell.addCells(data.toString(),newTargets);
		
		Turn turn = new Turn();
		turn.setIndice(indice);
		turn.setGameId(game.getGameId());
		turn.setDateCreated(new Date());
		
		turn.setAmountBot1(""+moneyA);
		turn.setAmountBot2(""+moneyB);
		turn.setAmountBot3(""+moneyC);
		turn.setAmountBot4(""+moneyD);
		
		turn.setData(data1);
		
		return turn;
	}
	
	
	private static void putCharAt(StringBuffer b, int pos, char val) {
		b.deleteCharAt(pos);
		b.insert(pos,val);
	}
	
	
	private static int getGain(char cell) {
		if(cell=='1') return 1;
		if(cell=='2') return 2;
		if(cell=='3') return 3;
		if(cell=='4') return 4;
		if(cell=='5') return 5;
		if(cell=='6') return 6;
		if(cell=='7') return 7;
		if(cell=='8') return 8;
		if(cell=='9') return 9;
		return -1;
	}
	
	
	private static int computeNewPos(int w, int h, int pos, String direction) {
		int[] pos2 = convert1Dto2D(w,h,pos);
		if(direction.equals("N")) pos2[0]--;
		if(direction.equals("S")) pos2[0]++;
		if(direction.equals("W")) pos2[1]--;
		if(direction.equals("E")) pos2[1]++;
		
		if(pos2[0]<0 || pos2[0]>=h) return -1;
		if(pos2[1]<0 || pos2[1]>=w) return -1;
		return convert2Dto1D(w, h, pos2[0], pos2[1]);
	}
	
	
	
	
	

	private static int[] convert1Dto2D(int w, int h, int pos) {
		return new int[]{pos/w,pos%h};
	}
	
	private static int convert2Dto1D(int w, int h, int x, int y) {
		return w*x+y;
	}
}
