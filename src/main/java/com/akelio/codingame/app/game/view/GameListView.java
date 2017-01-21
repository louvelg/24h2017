package com.akelio.codingame.app.game.view;

import java.util.List;
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
@ManagedBean(name = "gameListView")
@URLMapping(id = "viewGameList", pattern = "/game/list", viewId = "/faces/game/gameList.xhtml")
public class GameListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(GameListView.class);
	
	@Autowired
	private transient GameService	gameService;

	private List<Game>				gameList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		gameList = gameService.findAllGame(getUser());
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
