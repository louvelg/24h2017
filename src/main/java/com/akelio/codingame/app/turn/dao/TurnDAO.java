package com.akelio.codingame.app.turn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.akelio.base.BaseDAO;
import com.akelio.codingame.app.turn.entity.Turn;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class TurnDAO extends BaseDAO<Turn> {
	private static final String			TABLE_NAME	= "turn";
	private static final List<String>	TABLE_PKS	= Arrays.asList("turn_id");
	List<String>						fieldList	= Arrays.asList("turn_id", "indice", "game_id", "date_created", "amount_bot1", "amount_bot2", "amount_bot3", "amount_bot4", "data");

	public Turn findTurnById(String turnId) {
		return getOne("select " + fields() + " from turn where turn_id = ? ", new TurnMapper(), turnId);
	}

	public List<Turn> findAllTurn() {
		return getList("select " + fields() + " from turn ", new TurnMapper());
	}
	
	public List<Turn> findAllTurnForGame(String gameId) {
		return getList("select " + fields() + " from turn where game_id = ?", new TurnMapper(), gameId);
	}

	public void createTurn(Turn turn) {
		save(turn);
	}

	public void updateTurn(Turn turn) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);

		f.remove("date_created");

		String sql = "update turn set " + getUpdateCustomFields(f) + " where turn_id = :turnId ";
		update(sql, turn);
	}

	public void deleteTurn(String turnId) {
		delete("delete from turn where turn_id = ?", turnId);
	}

	private final class TurnMapper implements RowMapper<Turn> {
		public Turn mapRow(ResultSet rs, int rowNum) throws SQLException {
			Turn entity = new Turn();
			initEntity(entity, rs);
			return entity;
		}
	}

	protected String getTableName() {
		return TABLE_NAME;
	}

	protected List<String> getTablePKs() {
		return TABLE_PKS;
	}

	protected List<String> getFieldList() {
		return fieldList;
	}
}
