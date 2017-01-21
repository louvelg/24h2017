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
@ManagedBean(name = "moveCreateView")
@URLMapping(id = "viewMoveCreate", pattern = "/move/create", viewId = "/faces/move/moveCreate.xhtml")
public class MoveCreateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MoveCreateView.class);
	
	@Autowired
	private transient MoveService	moveService;

	private Move					move;

	@PostConstruct
	public void init() {
		initBean();
		move = new Move();
	}

	public String createMove() {
		
//		moveService.createMove(getUser(), move);
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
