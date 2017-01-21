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
	TurnService	turnService;
	public void init(Game game) {
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

	public Game findGameById(User currentUser, String gameId) {
		return gameDAO.findGameById(gameId);
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
