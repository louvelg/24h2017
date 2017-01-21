package com.akelio.codingame.app.game.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.game.service.GameService;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.base.BaseView;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "gameDetailsView")
@URLMapping(id = "viewGameDetails", pattern = "/game/#{/\\\\d+/gameId}", viewId = "/faces/game/gameDetails.xhtml")
public class GameDetailsView extends BaseView {

	private static final Logger		logger	= LoggerFactory.getLogger(GameDetailsView.class);

	@Autowired
	private transient GameService	gameService;

	private Game					game;
	private List<List<String>>		list;
	private int						turnIndice;
	private boolean					hasNext;
	private boolean					hasPrevious;
	private int						lastIndice;

	@PostConstruct
	public void init() {
		initBean();
		game = gameService.findGameById(getUser(), getParam("gameId"));
		lastIndice = game.getTurnList().size();
		turnIndice = 1;
		refreshListData();
	}

	private void refreshListData() {
		Map map = game.getMap();
		int height = Integer.valueOf(map.getHeight());
		int width = Integer.valueOf(map.getWidth());

		String data = null;

		hasNext = game.getTurnList().size() > turnIndice;
		hasPrevious = (turnIndice > 1);

		data = game.getTurnList().get(turnIndice - 1).getData();
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

	public String nextTurn() {
		if (turnIndice < game.getTurnList().size()) {
			turnIndice++;
		}
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

	public Logger getLogger() {
		return logger;
	}
}
