package com.akelio.codingame.app.game.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.game.dao.GameDAO;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.map.service.MapService;
import com.akelio.codingame.app.user.entity.User;

@Service("gameService")
public class GameService extends BaseService {

	@Autowired
	GameDAO	gameDAO;
	@Autowired
	MapService mapService;
	
	public Game findGameById(User currentUser, String gameId) {
		return gameDAO.findGameById(gameId);
	}

	public List<Game> findAllGame(User currentUser) {
		return gameDAO.findAllGame();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createGame(User currentUser, Game game) {
		
		mapService.createMap(currentUser);
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
