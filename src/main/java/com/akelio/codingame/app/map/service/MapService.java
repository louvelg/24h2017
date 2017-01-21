package com.akelio.codingame.app.map.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;
import com.akelio.codingame.app.map.dao.MapDAO;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.user.entity.User;
import com.akelio.codingame.util.UtilEngine;
import com.akelio.codingame.util.UtilMaze;

@Service("mapService")
public class MapService extends BaseService {

	@Autowired
	MapDAO	mapDAO;

	public Map findMapById(User currentUser, String mapId) {
		return mapDAO.findMapById(mapId);
		
		
	}

	public List<Map> findAllMap(User currentUser) {
		return mapDAO.findAllMap();
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Map createMap(User currentUser) {
		
		char[] cellsSomme = new char[]{'1','2','3','4','5','6'};
		char[] cellsBot = new char[]{'A','B','C','D'};
		
		int height = 50;
		int width = 50;
		
		int minSomme = 5;
		int maxSomme = 20;
		String mapName = "Map-"+UtilEngine.random(1000);
		
		Map map = new Map();
		
		map.setHeight(""+height);
		map.setWidth(""+width);
		map.setMinSomme(""+minSomme);
		map.setMaxSomme(""+maxSomme);
		map.setNbSomme(""+cellsSomme.length);
		map.setName(mapName);
		
		String data = UtilMaze.buildMaze(height,width);
		data = UtilMaze.addCells(data,cellsBot);
		data = UtilMaze.addCells(data,cellsSomme);
		
		map.setData(data);
		
		mapDAO.createMap(map);
		return map;
	}
	
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createMap(User currentUser, Map map) {
		mapDAO.createMap(map);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMap(User currentUser, Map map) {
		mapDAO.updateMap(map);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteMap(User currentUser, String mapId) {
		mapDAO.deleteMap(mapId);
	}

}
