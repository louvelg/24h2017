package com.akelio.codingame.app.turn.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.app.turn.service.TurnService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "turnUpdateView")
@URLMapping(id = "viewTurnUpdate", pattern = "/turn/update/#{/\\\\d+/turnId}", viewId = "/faces/turn/turnUpdate.xhtml")
public class TurnUpdateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(TurnUpdateView.class);
	
	@Autowired
	private transient TurnService	turnService;
	
	private Turn					turn;

	@PostConstruct
	public void init() {
		initBean();
		turn = turnService.findTurnById(getUser(), getParam("turnId"));
	}

	public String updateTurn() {
		turnService.updateTurn(getUser(), turn);
		return "pretty:viewTurnList";
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
