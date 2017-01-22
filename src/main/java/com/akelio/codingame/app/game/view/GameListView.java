package com.akelio.codingame.app.game.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.app.bot.entity.Bot;
import com.akelio.codingame.app.bot.service.BotService;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.game.service.GameService;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.app.turn.service.TurnService;
import com.akelio.codingame.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "gameListView")
@URLMapping(id = "viewGameList", pattern = "/game/list", viewId = "/faces/game/gameList.xhtml")
public class GameListView extends BaseView {

	private static final Logger		logger	= LoggerFactory.getLogger(GameListView.class);

	@Autowired
	private transient GameService	gameService;
	@Autowired
	private transient TurnService	turnService;
	@Autowired
	private transient BotService	botService;

	private List<Game>				gameList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		gameList = gameService.findAllGame(getUser());
		for (Game game : gameList) {
			StringBuffer buf = new StringBuffer();
			List<Turn> turnList = turnService.findAllTurnForGame(game.getGameId());
			Turn lastTurn = turnList.get(turnList.size() - 1);
			String winner = getWinner(lastTurn);
			Bot bot = botService.findBotById(null, game.getBot1Id());
			buf.append(bot.getName()).append(" : ").append(lastTurn.getAmountBot1()).append(" - ");
			if (winner.equals("A")) {
				game.setWinnerName(bot.getName() + " : " + lastTurn.getAmountBot1());
			}
			bot = botService.findBotById(null, game.getBot2Id());
			buf.append(bot.getName()).append(" : ").append(lastTurn.getAmountBot2()).append(" - ");
			if (winner.equals("B")) {
				game.setWinnerName(bot.getName() + " : " + lastTurn.getAmountBot2());
			}
			bot = botService.findBotById(null, game.getBot3Id());
			buf.append(bot.getName()).append(" : ").append(lastTurn.getAmountBot3()).append(" - ");
			if (winner.equals("C")) {
				game.setWinnerName(bot.getName() + " : " + lastTurn.getAmountBot3());
			}

			bot = botService.findBotById(null, game.getBot4Id());
			buf.append(bot.getName()).append(" : ").append(lastTurn.getAmountBot4());
			if (winner.equals("D")) {
				game.setWinnerName(bot.getName() + " : " + lastTurn.getAmountBot4());
			}

			game.setPlayerList(buf.toString());

		}
	}

	private String getWinner(Turn turn) {
		int max = 0;
		String winner = "";
		if (turn.getAmountBot1AsInt() > max) {
			winner = "A";
		}
		if (turn.getAmountBot2AsInt() > max) {
			winner = "B";
		}
		if (turn.getAmountBot3AsInt() > max) {
			winner = "C";
		}
		if (turn.getAmountBot4AsInt() > max) {
			winner = "D";
		}
		return winner;

	}

	public String deleteGame() {
		gameService.deleteGame(getUser(), getParam("gameId"));
		refresh();
		return "";
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public Logger getLogger() {
		return logger;
	}
}
