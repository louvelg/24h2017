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

	@PostConstruct
	public void init() {
		initBean();
		game = gameService.findGameById(getUser(), getParam("gameId"));
		Map map = game.getMap();
		String data = map.getData();

		int height = Integer.valueOf(map.getHeight());
		int width = Integer.valueOf(map.getWidth());
		String line = null;

		List<String> l1 = new ArrayList<>();
		list = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			line = data.substring(width * i, width * i + width);
			l1 = new ArrayList<>();
			for (int j = 0; j < line.length(); j++) {
				l1.add(String.valueOf(line.charAt(j)));
			}
			System.out.println(line);
			list.add(l1);
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

	public Logger getLogger() {
		return logger;
	}
}
