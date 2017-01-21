package com.akelio.codingame.app.game.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.akelio.base.BaseDAO;
import com.akelio.codingame.app.game.entity.Game;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class GameDAO extends BaseDAO<Game> {
	private static final String	TABLE_NAME	= "game";
	private static final List<String>	TABLE_PKS	= Arrays.asList("game_id");
	List<String>				fieldList	= Arrays.asList("game_id","name","map_id","bot1_id","bot2_id","bot3_id","bot4_id","date_created");

	public Game findGameById(String gameId) {
		return getOne("select " + fields() + " from game where game_id = ? ", new GameMapper(), gameId);
	}
	
	public Game findGameByName(String gameName) {
		return getOne("select " + fields() + " from game where name = ? ", new GameMapper(), gameName);
	}
	
	public List<Game> findAllGame() {
		return getList("select " + fields() + " from game order by date_created desc", new GameMapper());
	}

	
	public void createGame(Game game) {
		save(game);
	}

	public void updateGame(Game game) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		
		f.remove("date_created");
		
		String sql = "update game set " + getUpdateCustomFields(f) + " where game_id = :gameId ";
		update(sql, game);
	}

	public void deleteGame(String gameId) {
		delete("delete from game where game_id = ?", gameId);
	}

	private final class GameMapper implements RowMapper<Game> {
		public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
			Game entity = new Game();
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
