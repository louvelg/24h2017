package com.akelio.codingame.app.bot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.akelio.base.BaseDAO;
import com.akelio.codingame.app.bot.entity.Bot;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class BotDAO extends BaseDAO<Bot> {
	private static final String			TABLE_NAME	= "bot";
	private static final List<String>	TABLE_PKS	= Arrays.asList("bot_id");
	List<String>						fieldList	= Arrays.asList("bot_id", "name", "user_id", "date_created");

	public Bot findBotById(String botId) {
		return getOne("select " + fields() + " from bot where bot_id = ? ", new BotMapper(), botId);
	}

	public List<Bot> findAllBot() {
		return getList("select " + fields() + " from bot ", new BotMapper());
	}

	public List<Bot> findAllMyBot(String userId) {
		return getList("select " + fields() + " from bot where user_id = ?", new BotMapper(), userId);
	}

	public void createBot(Bot bot) {
		save(bot);
	}

	public void updateBot(Bot bot) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);

		f.remove("date_created");

		String sql = "update bot set " + getUpdateCustomFields(f) + " where bot_id = :botId ";
		update(sql, bot);
	}

	public void deleteBot(String botId) {
		delete("delete from bot where bot_id = ?", botId);
	}

	private final class BotMapper implements RowMapper<Bot> {
		public Bot mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bot entity = new Bot();
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
