package com.akelio.codingame.app.turn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.turn.dao.TurnDAO;
import com.akelio.codingame.app.turn.entity.Turn;
import com.akelio.codingame.app.user.entity.User;

@Service("turnService")
public class TurnService extends BaseService {

	@Autowired
	TurnDAO	turnDAO;
	
	public Turn findTurnById(User currentUser, String turnId) {
		return turnDAO.findTurnById(turnId);
	}

	public List<Turn> findAllTurn(User currentUser) {
		return turnDAO.findAllTurn();
	}
	
	public List<Turn> findAllTurnForGame(String gameId) {
		return turnDAO.findAllTurnForGame(gameId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createTurn(User currentUser, Turn turn) {
		turnDAO.createTurn(turn);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createTurn(Turn turn) {
		turnDAO.createTurn(turn);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTurn(User currentUser, Turn turn) {
		turnDAO.updateTurn(turn);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTurn(User currentUser, String turnId) {
		turnDAO.deleteTurn(turnId);
	}
	
	
}
