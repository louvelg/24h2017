package com.akelio.codingame.app.game.ws;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.game.service.GameService;
import com.akelio.codingame.base.BaseWS;

@RestController
public class GameWS extends BaseWS {
	// curl -i --user admin:admin -X GET http://localhost:8080/codingame/rest/v1/game/22
	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/game/22

	@Autowired
	GameService	gameService;

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/game/22
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
	public Game findGameById(@PathVariable String gameId) {
		Game game = gameService.findGameById(null, gameId);
		return game;
	}

	// curl -i  -X GET http://localhost:8080/codingame/rest/v1/game/list
	@RequestMapping(value = "/game/list", method = RequestMethod.GET)
	public List<Game> getGameList() {
		List<Game> gameList = gameService.findAllGame(getUser());
		return gameList;
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -H "Content-type: application/json" -X POST http://localhost:8080/codingame/rest/v1/game -d "{\"gameId\":gameId, \"name\":name, \"mapId\":mapId, \"bot1Id\":bot1Id, \"bot2Id\":bot2Id, \"bot3Id\":bot3Id, \"bot4Id\":bot4Id}"
	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public Game createGame() {
		Game game = new Game();
		gameService.createGame(getUser(), game);
		game.setName("game" + game.getGameId());
		return game;
	}

	// curl -i -X GET http://localhost:8080/codingame/rest/v1/user/1/signin/2
	@RequestMapping(value = "/user/{botId}/signin/{gameId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Game signinGame(@PathVariable String botId, @PathVariable String gameId) {
		Game game = gameService.findGameById(getUser(), gameId);
		int index = game.setNextBotId(botId);
		gameService.updateGame(getUser(), game);
		int nbRetry = 0;
		while (game.isPending() && nbRetry < 50) {
			game = gameService.findGameById(getUser(), gameId);
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
			nbRetry++;
		}
		if (game.isPending()) {
			Game g = new Game();
			g.setStatus("ko : pas assez de joueur");
			return g;
		}
		gameService.init(game);

		return game;
	}

	// curl -i -X GET http://localhost:8080/codingame/rest/v1/bot/1/move/N
	@RequestMapping(value = "/bot/{botId}/game/{gameId}/move/{move}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Game move(@PathVariable String botId,@PathVariable String gameId, @PathVariable String move) {
		Game game = gameService.findGameById(getUser(), gameId);
		gameService.nextTurn(game);
		return game;
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X DELETE http://localhost:8080/codingame/rest/v1/game/22
	@RequestMapping(value = "/game/{gameId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteGame(@PathVariable String gameId) {
		gameService.deleteGame(getUser(), gameId);
	}
}
