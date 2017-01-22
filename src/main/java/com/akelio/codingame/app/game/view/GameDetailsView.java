package com.akelio.codingame.app.game.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "gameDetailsView")
@URLMapping(id = "viewGameDetails", pattern = "/game/#{/\\\\d+/gameId}", viewId = "/faces/game/gameDetails.xhtml")
public class GameDetailsView extends BaseView {

	private static final Logger		logger	= LoggerFactory.getLogger(GameDetailsView.class);

	@Autowired
	private transient GameService	gameService;
	@Autowired
	private transient BotService	botService;

	private Game					game;
	private List<List<String>>		list;
	private int						turnIndice;
	private boolean					hasNext;
	private boolean					hasPrevious;
	private int						lastIndice;
	private String					percent;

	private int						amount1;
	private int						amount2;
	private int						amount3;
	private int						amount4;

	private String					bot1Name;
	private String					bot2Name;
	private String					bot3Name;
	private String					bot4Name;

	private String					winner;
	private String					winnerFinal;

	@PostConstruct
	public void init() {
		initBean();
		game = gameService.findGameById(getUser(), getParam("gameId"));

		lastIndice = game.getTurnList().size();
		turnIndice = 1;
		initBotName();
		refreshListData();
	}

	private void initBotName() {
		Bot bot = botService.findBotById(null, game.getBot1Id());
		bot1Name = bot.getName();
		bot = botService.findBotById(null, game.getBot2Id());
		bot2Name = bot.getName();
		bot = botService.findBotById(null, game.getBot3Id());
		bot3Name = bot.getName();
		bot = botService.findBotById(null, game.getBot4Id());
		bot4Name = bot.getName();
	}

	private void refreshListData() {
		Map map = game.getMap();
		int height = Integer.valueOf(map.getHeight());
		int width = Integer.valueOf(map.getWidth());

		percent = buildPercentDisplay(lastIndice, turnIndice);
		String data = null;

		hasNext = game.getTurnList().size() > turnIndice;
		hasPrevious = (turnIndice > 1);
		Turn currentTurn = game.getTurnList().get(turnIndice - 1);
		data = currentTurn.getData();
		amount1 = currentTurn.getAmountBot1AsInt();
		amount2 = currentTurn.getAmountBot2AsInt();
		amount3 = currentTurn.getAmountBot3AsInt();
		amount4 = currentTurn.getAmountBot4AsInt();
		updateWinner();
		if (!hasNext) {
			winnerFinal = winner;
		}
		String line = null;
		List<String> l1 = new ArrayList<>();
		list = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			line = data.substring(width * i, width * i + width);
			l1 = new ArrayList<>();
			for (int j = 0; j < line.length(); j++) {
				l1.add(String.valueOf(line.charAt(j)).replace(" ", "empty"));
			}
			System.out.println(line);
			list.add(l1);
		}
	}

	private void updateWinner() {
		int max = 0;
		winner = "";
		if (amount1 > max) {
			max = amount1;
			winner = "A";
		}
		if (amount2 > max) {
			max = amount2;
			winner = "B";
		}
		if (amount3 > max) {
			max = amount3;
			winner = "C";
		}
		if (amount4 > max) {
			max = amount4;
			winner = "D";
		}

	}

	public String nextTurn() {
		game = gameService.findGameById(getUser(), getParam("gameId"));
		if (turnIndice < game.getTurnList().size()) {
			turnIndice++;
		}
		refreshListData();
		return "";
	}

	public String lastTurn() {
		game = gameService.findGameById(getUser(), getParam("gameId"));
		turnIndice = game.getTurnList().size();
		refreshListData();
		return "";
	}

	public String firstTurn() {
		turnIndice = 1;
		refreshListData();
		return "";
	}

	public String previousTurn() {
		if (turnIndice > 1) {
			turnIndice--;
		}
		refreshListData();
		return "";
	}

	public String buildPercentDisplay(int total, int finish) {
		if (total == 0) return "1%";
		double val = (Double.valueOf(finish) / Double.valueOf(total)) * 100;
		return roundStringWithZeroDecimal(val) + "%";
	}

	private static String roundStringWithZeroDecimal(double val) {
		try {
			BigDecimal bd = new BigDecimal(val);
			return "" + bd.setScale(0, RoundingMode.HALF_UP).intValue();
		} catch (NumberFormatException e) {
			return "" + val;
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<List<String>> getList() {
		return list;
	}

	public void setList(List<List<String>> list) {
		this.list = list;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public int getTurnIndice() {
		return turnIndice;
	}

	public void setTurnIndice(int turnIndice) {
		this.turnIndice = turnIndice;
	}

	public int getLastIndice() {
		return lastIndice;
	}

	public void setLastIndice(int lastIndice) {
		this.lastIndice = lastIndice;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public int getAmount1() {
		return amount1;
	}

	public void setAmount1(int amount1) {
		this.amount1 = amount1;
	}

	public int getAmount2() {
		return amount2;
	}

	public void setAmount2(int amount2) {
		this.amount2 = amount2;
	}

	public int getAmount3() {
		return amount3;
	}

	public void setAmount3(int amount3) {
		this.amount3 = amount3;
	}

	public int getAmount4() {
		return amount4;
	}

	public void setAmount4(int amount4) {
		this.amount4 = amount4;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWinnerFinal() {
		return winnerFinal;
	}

	public void setWinnerFinal(String winnerFinal) {
		this.winnerFinal = winnerFinal;
	}

	public String getBot1Name() {
		return bot1Name;
	}

	public void setBot1Name(String bot1Name) {
		this.bot1Name = bot1Name;
	}

	public String getBot2Name() {
		return bot2Name;
	}

	public void setBot2Name(String bot2Name) {
		this.bot2Name = bot2Name;
	}

	public String getBot3Name() {
		return bot3Name;
	}

	public void setBot3Name(String bot3Name) {
		this.bot3Name = bot3Name;
	}

	public String getBot4Name() {
		return bot4Name;
	}

	public void setBot4Name(String bot4Name) {
		this.bot4Name = bot4Name;
	}

	public Logger getLogger() {
		return logger;
	}
}
