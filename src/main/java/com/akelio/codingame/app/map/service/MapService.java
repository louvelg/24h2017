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
	public Map createMap(User currentUser) {
		Map map = new Map();
		map.setHeight("10");
		map.setWidth("10");
		map.setMinSomme("5");
		map.setMaxSomme("20");
		map.setNbSomme("4");
		map.setName("Map name");
		int height = Integer.valueOf(map.getHeight());
		int width = Integer.valueOf(map.getWidth());

		String[][] m = new String[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				m[i][j] = " ";
			}
		}
		m[0][0] = "A";
		m[height - 1][0] = "B";
		m[0][width - 1] = "C";
		m[height - 1][width - 1] = "D";

		m[2][2] = "5";
		m[height - 3][2] = "5";
		m[2][width - 3] = "5";
		m[height - 3][width - 3] = "9";
		
		map.setData(
				 "A        C"
				+"          "
				+"  4   5   "
				+"          "
				+"          "
				+"          "
				+"          "
				+"  4   6   "
				+"          "
				+"B        D");
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
