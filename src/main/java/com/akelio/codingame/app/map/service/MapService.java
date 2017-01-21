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
	public void createMap(User currentUser) {
		Map map = new Map();
		map.setHeight("10");
		map.setWidth("10");
		map.setMinSomme("5");
		map.setMaxSomme("20");
		map.setNbSomme("5");
		map.setName("Map name");
		map.setData("");
		int height = Integer.valueOf(map.getHeight());
		int width = Integer.valueOf(map.getWidth());

		String[][] m = new String[width][height];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				m[i][j] = "0";
			}
		}
		m[0][0] = "B1";
		m[height - 1][0] = "B2";
		m[0][width - 1] = "B3";
		m[height - 1][width - 1] = "B1";

		m[2][2] = "5";
		m[height - 3][2] = "5";
		m[2][width - 3] = "5";
		m[height - 3][width - 3] = "10";

		mapDAO.createMap(map);
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
