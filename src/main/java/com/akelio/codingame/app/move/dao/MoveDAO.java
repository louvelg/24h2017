package com.akelio.codingame.app.move.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.akelio.base.BaseDAO;
import com.akelio.codingame.app.move.entity.Move;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class MoveDAO extends BaseDAO<Move> {
	private static final String			TABLE_NAME	= "move";
	private static final List<String>	TABLE_PKS	= Arrays.asList("move_id");
	List<String>						fieldList	= Arrays.asList("move_id", "game_id", "turn_id", "indice", "bot_id", "bot_name", "direction", "date_created");

	public Move findMoveById(String moveId) {
		return getOne("select " + fields() + " from move where move_id = ? ", new MoveMapper(), moveId);
	}
	
	public Move findMoveByGameAndIndice(String gameId, String indice) {
		return getOne("select " + fields() + " from move where game_id = ? and indice = ? ", new MoveMapper(), gameId, indice);
	}

	public List<Move> findAllMove() {
		return getList("select " + fields() + " from move ", new MoveMapper());
	}
	
	public List<Move> findAllMoveForGameAndIndice(String gameId, String indice) {
		return getList("select " + fields() + " from move where game_id = ? and indice = ? order by bot_name", new MoveMapper(), gameId, indice);
	}

	public int countNbMoveForIndice(String gameId, String indice) {
		return getInt("select count(*) from move where game_id = ? and indice = ?", gameId, indice);
	}

	public void createMove(Move move) {
		save(move);
	}

	public void updateMove(Move move) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);

		f.remove("date_created");

		String sql = "update move set " + getUpdateCustomFields(f) + " where move_id = :moveId ";
		update(sql, move);
	}

	public void deleteMove(String moveId) {
		delete("delete from move where move_id = ?", moveId);
	}

	private final class MoveMapper implements RowMapper<Move> {
		public Move mapRow(ResultSet rs, int rowNum) throws SQLException {
			Move entity = new Move();
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
