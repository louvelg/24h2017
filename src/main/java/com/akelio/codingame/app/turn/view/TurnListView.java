package com.akelio.codingame.app.turn.view;

import java.util.List;
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
@ManagedBean(name = "turnListView")
@URLMapping(id = "viewTurnList", pattern = "/turn/list", viewId = "/faces/turn/turnList.xhtml")
public class TurnListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(TurnListView.class);
	
	@Autowired
	private transient TurnService	turnService;

	private List<Turn>				turnList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		turnList = turnService.findAllTurn(getUser());
	}
	
	public String deleteTurn() {
		turnService.deleteTurn(getUser(), getParam("turnId"));
		refresh();
		return "";
	}
	
	public List<Turn> getTurnList() {
		return turnList;
	}

	public void setTurnList(List<Turn> turnList) {
		this.turnList = turnList;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
