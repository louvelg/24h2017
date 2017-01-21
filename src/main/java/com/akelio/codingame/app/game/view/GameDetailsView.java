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
@ManagedBean(name = "gameDetailsView")
@URLMapping(id = "viewGameDetails", pattern = "/game/#{/\\\\d+/gameId}", viewId = "/faces/game/gameDetails.xhtml")
public class GameDetailsView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(GameDetailsView.class);
	
	@Autowired
	private transient GameService	gameService;

	private Game					game;

	@PostConstruct
	public void init() {
		initBean();
		game = gameService.findGameById(getUser(), getParam("gameId"));
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
