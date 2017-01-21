package com.akelio.codingame.app.map.ws;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.akelio.codingame.base.BaseWS;
import com.akelio.codingame.app.map.service.MapService;
import com.akelio.codingame.app.map.entity.Map;

@RestController
public class MapWS extends BaseWS {
	// curl -i --user admin:admin -X GET http://localhost:8080/codingame/rest/v1/map/22
	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/map/22
	
	@Autowired
	MapService	mapService;
	
	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/map/22
	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.GET)
	public Map findMapById(@PathVariable String mapId) {
		Map map = mapService.findMapById(getUser(), mapId);
		return map;
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/map/list
	@RequestMapping(value = "/map/list", method = RequestMethod.GET)
	public List<Map> getMapList() {
		List<Map> mapList = mapService.findAllMap(getUser());
		return mapList;
	}
	
	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -H "Content-type: application/json" -X POST http://localhost:8080/codingame/rest/v1/map -d "{\"mapId\":mapId, \"name\":name, \"width\":width, \"height\":height, \"data\":data, \"nbSomme\":nbSomme, \"maxSomme\":maxSomme, \"minSomme\":minSomme}"
	@RequestMapping(value = "/map", method = RequestMethod.POST)
	public Map createMap(@RequestBody Map map) {
		mapService.createMap(getUser(), map);
		return map;
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -H "Content-type: application/json" -X PUT http://localhost:8080/codingame/rest/v1/map/22 -d "{\"mapId\":mapId, \"name\":name, \"width\":width, \"height\":height, \"data\":data, \"nbSomme\":nbSomme, \"maxSomme\":maxSomme, \"minSomme\":minSomme, \"dateCreated\":dateCreated}"
	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updatTask(@PathVariable String mapId, @RequestBody Map map) {
		mapService.updateMap(getUser(), map);
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X DELETE http://localhost:8080/codingame/rest/v1/map/22
	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMap(@PathVariable String mapId) {
		mapService.deleteMap(getUser(), mapId);
	}
}
