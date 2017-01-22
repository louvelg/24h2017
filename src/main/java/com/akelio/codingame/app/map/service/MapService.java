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
import com.akelio.codingame.util.UtilCell;
import com.akelio.codingame.util.UtilEngine;
import com.akelio.codingame.util.UtilMaze;
import com.akelio.codingame.util.UtilRandom;

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
	public Map createMap(User currentUser, String params) {
		
		//type 0 : labyrithme
		//type 1 : blocs aléatoires (dense)
		//type 2 : blocs aléatoires (moyen)
		//type 3 : blocs aléatoires (parsemé)
		//type 4 : blocs aléatoires (très parsemé)
		//type 5 : pas de blocs (map vide)
		
		if(params.equals("no"))
			params = "20-20-5-1-9-6";
		
		String[] nn = params.split("-");
		
		int height = toInt(nn[0]);
		int width = toInt(nn[1]);
		int mapType = toInt(nn[2]);
		int minSomme = toInt(nn[3]);
		int maxSomme = toInt(nn[4]);
		int nbSomme = toInt(nn[5]);
		
		char[] cellsBot = new char[]{'A','B','C','D'};
		char[] cellsSomme = UtilEngine.buildSommeCells(nbSomme,minSomme,maxSomme);	
		String mapName = "Map-"+UtilRandom.random(1000);
		
		
		Map map = new Map();
		
		map.setHeight(""+height);
		map.setWidth(""+width);
		map.setMinSomme(""+minSomme);
		map.setMaxSomme(""+maxSomme);
		map.setNbSomme(""+nbSomme);
		map.setName(mapName);
		
		String data = UtilMaze.buildMaze(height,width,mapType);
		data = UtilCell.addCells(data,cellsBot);
		data = UtilCell.addCells(data,cellsSomme);
		
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
	
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}

}
