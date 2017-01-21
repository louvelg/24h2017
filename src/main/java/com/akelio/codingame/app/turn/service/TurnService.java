package com.akelio.codingame.app.turn.service;

import java.util.List;

import javax.print.attribute.standard.PrinterLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.game.service.GameService;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.map.service.MapService;
import com.akelio.codingame.app.turn.dao.TurnDAO;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.app.user.entity.User;

@Service("turnService")
public class TurnService extends BaseService {

	@Autowired
	TurnDAO	turnDAO;
	
	@Autowired
	GameService	gameService;
	
	@Autowired
	MapService	mapService;
	
	public Turn findTurnById(User currentUser, String turnId) {
		return turnDAO.findTurnById(turnId);
	}
	
	public Turn findTurnForGameAndIndice(String gameId, String indice) {
		return turnDAO.findTurnForGameAndIndice(gameId, indice);
	}

	public List<Turn> findAllTurn(User currentUser) {
		return turnDAO.findAllTurn();
	}
	
	public List<Turn> findAllTurnForGame(String gameId) {
		return turnDAO.findAllTurnForGame(gameId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createTurn(User currentUser, Turn turn) {
		turnDAO.createTurn(turn);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized boolean createTurn(Turn turn) {
		if (findTurnForGameAndIndice(turn.getGameId(), turn.getIndice()) != null)
			return false;
		turnDAO.createTurn(turn);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTurn(User currentUser, Turn turn) {
		turnDAO.updateTurn(turn);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTurn(User currentUser, String turnId) {
		turnDAO.deleteTurn(turnId);
	}
	
	public void printTurn(Turn turn) {
		Game game = gameService.findGameById(null, turn.getGameId());
		Map map = mapService.findMapById(null, game.getMapId());
				
		String g = turn.getData();
		String line = null;
		
		int height = map.getHeightAsInt();
		int width = map.getWidthAsInt();
	
		System.out.println("Game ID :: " + turn.getGameId() + " -- Turn ID :: " + turn.getTurnId()
		+ "\n"
		+"***********************************************");
		
		for (int i = 0; i < height; i++) {
			line = "***|" + g.substring(width*i, width*i+width) + "|***";
			System.out.println(line);
		}
		
		System.out.println("***********************************************");
	}
}
