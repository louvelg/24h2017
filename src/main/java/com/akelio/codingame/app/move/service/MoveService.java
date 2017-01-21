package com.akelio.codingame.app.move.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.game.entity.Game;
import com.akelio.codingame.app.move.dao.MoveDAO;
import com.akelio.codingame.app.move.entity.Move;
import com.akelio.codingame.app.user.entity.User;

@Service("moveService")
public class MoveService extends BaseService {

	@Autowired
	MoveDAO	moveDAO;
	
	public Move findMoveById(User currentUser, String moveId) {
		return moveDAO.findMoveById(moveId);
	}

	public List<Move> findAllMove(User currentUser) {
		return moveDAO.findAllMove();
	}

	public List<Move> findAllMoveForGameAndIndice(String gameId, String indice) {
		return moveDAO.findAllMoveForGameAndIndice(gameId, indice);
	}
	
	public int countNbMoveForIndice(String gameId, String indice) {
		return moveDAO.countNbMoveForIndice(gameId, indice);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createMove(User currentUser, String indiceBot, Move move) {
		Move m = moveDAO.findMoveByGameAndIndice(move.getGameId(), move.getIndice(), indiceBot);
		if (m == null) {
			moveDAO.createMove(move);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMove(User currentUser, Move move) {
		moveDAO.updateMove(move);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteMove(User currentUser, String moveId) {
		moveDAO.deleteMove(moveId);
	}
	
	
	
	public Move[] find4Moves(Game game, String indice) {
		List<Move> moveList = findAllMoveForGameAndIndice(game.getGameId(), indice);
		Move move1 = null;
		Move move2 = null;
		Move move3 = null;
		Move move4 = null;
		
		for (Move m : moveList) {
			if (m.getBotName().equals("A")) {
				move1 = m;
			}
			if (m.getBotName().equals("B")) {
				move2 = m;
			}
			if (m.getBotName().equals("C")) {
				move3 = m;
			}
			if (m.getBotName().equals("D")) {
				move4 = m;
			}
		}
		return new Move[]{move1,move2,move3,move4};
	}
	
	
}
