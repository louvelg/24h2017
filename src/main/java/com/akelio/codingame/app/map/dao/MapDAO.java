package com.akelio.codingame.app.map.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.akelio.base.BaseDAO;
import com.akelio.codingame.app.map.entity.Map;
import com.gs.collections.impl.list.mutable.FastList;

@Repository
public class MapDAO extends BaseDAO<Map> {
	private static final String	TABLE_NAME	= "map";
	private static final List<String>	TABLE_PKS	= Arrays.asList("map_id");
	List<String>				fieldList	= Arrays.asList("map_id","name","width","height","data","nb_somme","max_somme","min_somme","date_created");

	public Map findMapById(String mapId) {
		return getOne("select " + fields() + " from map where map_id = ? ", new MapMapper(), mapId);
	}

	public List<Map> findAllMap() {
		return getList("select " + fields() + " from map ", new MapMapper());
	}

	
	public void createMap(Map map) {
		save(map);
	}

	public void updateMap(Map map) {
		FastList<String> f = new FastList<String>(fieldList);
		f.remove(TABLE_PKS);
		
		f.remove("date_created");
		
		String sql = "update map set " + getUpdateCustomFields(f) + " where map_id = :mapId ";
		update(sql, map);
	}

	public void deleteMap(String mapId) {
		delete("delete from map where map_id = ?", mapId);
	}

	private final class MapMapper implements RowMapper<Map> {
		public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map entity = new Map();
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
