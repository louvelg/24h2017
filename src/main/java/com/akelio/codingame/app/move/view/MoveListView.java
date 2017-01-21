package com.akelio.codingame.app.move.view;

import java.util.List;
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
@ManagedBean(name = "moveListView")
@URLMapping(id = "viewMoveList", pattern = "/move/list", viewId = "/faces/move/moveList.xhtml")
public class MoveListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MoveListView.class);
	
	@Autowired
	private transient MoveService	moveService;

	private List<Move>				moveList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		moveList = moveService.findAllMove(getUser());
	}
	
	public String deleteMove() {
		moveService.deleteMove(getUser(), getParam("moveId"));
		refresh();
		return "";
	}
	
	public List<Move> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<Move> moveList) {
		this.moveList = moveList;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
