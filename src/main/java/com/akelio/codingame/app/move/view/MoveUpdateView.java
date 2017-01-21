package com.akelio.codingame.app.move.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.move.service.MoveService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "moveUpdateView")
@URLMapping(id = "viewMoveUpdate", pattern = "/move/update/#{/\\\\d+/moveId}", viewId = "/faces/move/moveUpdate.xhtml")
public class MoveUpdateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MoveUpdateView.class);
	
	@Autowired
	private transient MoveService	moveService;
	
	private Move					move;

	@PostConstruct
	public void init() {
		initBean();
		move = moveService.findMoveById(getUser(), getParam("moveId"));
	}

	public String updateMove() {
		moveService.updateMove(getUser(), move);
		return "pretty:viewMoveList";
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
