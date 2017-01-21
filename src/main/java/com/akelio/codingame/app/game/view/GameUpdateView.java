package com.akelio.codingame.app.game.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.game.service.GameService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "gameUpdateView")
@URLMapping(id = "viewGameUpdate", pattern = "/game/update/#{/\\\\d+/gameId}", viewId = "/faces/game/gameUpdate.xhtml")
public class GameUpdateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(GameUpdateView.class);
	
	@Autowired
	private transient GameService	gameService;
	
	private Game					game;

	@PostConstruct
	public void init() {
		initBean();
		game = gameService.findGameById(getUser(), getParam("gameId"));
	}

	public String updateGame() {
		gameService.updateGame(getUser(), game);
		return "pretty:viewGameList";
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
