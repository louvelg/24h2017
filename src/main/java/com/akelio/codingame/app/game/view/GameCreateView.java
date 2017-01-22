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
@ManagedBean(name = "gameCreateView")
@URLMapping(id = "viewGameCreate", pattern = "/game/create", viewId = "/faces/game/gameCreate.xhtml")
public class GameCreateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(GameCreateView.class);
	
	@Autowired
	private transient GameService	gameService;

	private Game					game;

	@PostConstruct
	public void init() {
		initBean();
		game = new Game();
	}

	public String createGame() {
		
		gameService.createGame(getUser(), game, null);
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
