package com.akelio.codingame.app.game.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.game.dao.GameDAO;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.map.service.MapService;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.move.service.MoveService;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.app.turn.service.TurnService;
import com.akelio.codingame.app.user.entity.User;
import com.akelio.codingame.util.UtilEngine;
import com.akelio.codingame.util.UtilThread;

@Service("gameService")
public class GameService extends BaseService {

	@Autowired
	GameDAO		gameDAO;
	@Autowired
	MapService	mapService;
	@Autowired
	MoveService	moveService;
	@Autowired
	TurnService	turnService;
	
	
	public synchronized Game initFirstTurn(String gameId) {
		Game game = findGameById(null, gameId);
		if(game.hasTurns()) return game;
		
		String mapId = game.getMapId();
		Map map = mapService.findMapById(null, mapId);
		
		Turn turn = new Turn();
		
		turn.setDateCreated(new Date());
		turn.setIndice("1");
		turn.setGameId(game.getGameId());
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		
		turn.setData(map.getData());
		
		turnService.createTurn(turn);
		game.getTurnList().add(turn);
		return game;
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized int addNewBot(User currentUser, String gameId, String botId) {
		Game game = findGameById(currentUser, gameId);
		int index = game.setNextBotId(botId);
		updateGame(currentUser, game);
		return index;
	}

	
	
	public Game nextTurn(String gameId, String botName, String direction) {
		Game game = findGameById(null, gameId);
		
		String indice = ""+(game.getTurnList().size()+1);
		System.out.println("Game : " + gameId + " Bot : " + botName +  " direction : " + direction + " Tour : " + indice );
		
		Move move = new Move();
		move.setDirection(direction);
		move.setGameId(game.getGameId());
		move.setBotId(game.getBotIdForName(botName));
		move.setIndice(indice);
		move.setBotName(botName);
		moveService.createMove(null,botName,  move);
		
		int nbRetry = 0;
		int nbMove = moveService.countNbMoveForIndice(gameId, indice);
		while (nbMove < 4 && nbRetry <50) {
			nbMove = moveService.countNbMoveForIndice(gameId, indice);
			UtilThread.sleep(500);
			nbRetry++;
		}

		Move[] moves = moveService.find4Moves(game, indice);
		Turn nextTurn = UtilEngine.computeNextTurn(game,indice,moves);
		if(nextTurn==null) return game;
		
		boolean created = turnService.createTurn(nextTurn);
		if (created) {
			game.getTurnList().add(nextTurn);
			turnService.printTurn(nextTurn);
			
			if(game.isOver()) {
				game.setStatus("over");
				updateGame(null, game);
			}
			return game;
		}
		
		return findGameById(null, game.getGameId());
	}
	
	

	public Game findGameById(User currentUser, String gameId) {
		List<Turn> turnList = turnService.findAllTurnForGame(gameId);
		Game game = gameDAO.findGameById(gameId);
		Map map = mapService.findMapById(null, game.getMapId());
		game.setMap(map);
		game.setTurnList(turnList);
		return game;
	}

	public Game findGameByName(User currentUser, String gameName) {
		return gameDAO.findGameByName(gameName);
	}

	public List<Game> findAllGame(User currentUser) {
		return gameDAO.findAllGame();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createGame(User currentUser, Game game) {
		Map map = mapService.createMap(currentUser);
		game.setMapId(map.getMapId());
		gameDAO.createGame(game);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateGame(User currentUser, Game game) {
		gameDAO.updateGame(game);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteGame(User currentUser, String gameId) {
		gameDAO.deleteGame(gameId);
	}

}
