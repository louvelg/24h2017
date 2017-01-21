package com.akelio.codingame.app.game.service;

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
	
	
	public synchronized void init(String gameId) {
		Game game = findGameById(null, gameId);
		if(game.hasTurns()) return;
		
		Turn turn = new Turn();
		turn.setIndice("1");
		turn.setGameId(game.getGameId());
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		turn.setData(
				 "A        C"
				+"          "
				+"  4   5   "
				+"          "
				+"          "
				+"          "
				+"          "
				+"  4   6   "
				+"          "
				+"B        D");
		
		turnService.createTurn(turn);
		game.setLastTurn(turn);
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized int addNewBot(User currentUser, String gameId, String botId) {
		Game game = findGameById(currentUser, gameId);
		int index = game.setNextBotId(botId);
		updateGame(currentUser, game);
		return index;
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized Game nextTurn(String gameId, String indiceBot, String direction) {
		Move move = new Move();
		Game game = findGameById(null, gameId);
		
		
		String indice = String.valueOf(game.getTurnList().size()+1);
		move.setDirection(direction);
		move.setGameId(game.getGameId());
		if (indiceBot.equals("A")) {
			move.setBotId(game.getBot1Id());
		}
		if (indiceBot.equals("B")) {
			move.setBotId(game.getBot2Id());
		}
		if (indiceBot.equals("C")) {
			move.setBotId(game.getBot3Id());
		}
		if (indiceBot.equals("D")) {
			move.setBotId(game.getBot4Id());
		}
		move.setIndice(indice);
		move.setBotName(indiceBot);
		moveService.createMove(null, move);
		
		
		int nbRetry = 0;
		int nbMove = moveService.countNbMoveForIndice(gameId, indice);
		while (nbMove < 4 && nbRetry <50) {
			nbMove = moveService.countNbMoveForIndice(gameId, indice);
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
			nbRetry++;
		}
		String lastMap = "";
		if (game.hasTurns()) {
			lastMap = game.getTurnList().get(game.getTurnList().size()-1).getData();
		} else {
			Map map = mapService.findMapById(null, game.getMapId());
			lastMap = map.getData();
		}
		
		//On récupere la liste des moves pour le tour
		List<Move> moveList = moveService.findAllMoveForGameAndIndice(game.getGameId(), indice);
		Move move1 = null;
		Move move2 = null;
		Move move3 = null;
		Move move4 = null;
		
		for (Move m : moveList) {
			if (m.getBotName().equals("A")) {
				move1 = m;
			}
			if (m.getBotName().equals("B")) {
				move2 = m;
			}
			if (m.getBotName().equals("C")) {
				move3 = m;
			}
			if (m.getBotName().equals("D")) {
				move4 = m;
			}
		}
		
		Turn turn = createNextTurn(game, applyMoveToMap(lastMap, move1, move2, move3, move4));
		game.setLastTurn(turn);
		game.getTurnList().add(turn);
		return game;
	}

	private String applyMoveToMap(String map, Move move1, Move move2, Move move3, Move move4) {
		return map;
	}
	
	
	
	
	private Turn createNextTurn(Game game, String nextMap) {
		Turn turn = new Turn();
		turn.setIndice(String.valueOf(game.getTurnList().size()+1));
		turn.setGameId(game.getGameId());
		turn.setAmountBot1("0");
		turn.setAmountBot2("0");
		turn.setAmountBot3("0");
		turn.setAmountBot4("0");
		turn.setData(nextMap);
		turnService.createTurn(turn);
		game.setLastTurn(turn);
		game.getTurnList().add(turn);
		turnService.printTurn(turn);
		return turn;
	}

	
	
	
	public Game findGameById(User currentUser, String gameId) {
		List<Turn> turnList = turnService.findAllTurnForGame(gameId);
		Game game = gameDAO.findGameById(gameId);
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
