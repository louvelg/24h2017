package com.akelio.codingame.app.map.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.map.service.MapService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "mapUpdateView")
@URLMapping(id = "viewMapUpdate", pattern = "/map/update/#{/\\\\d+/mapId}", viewId = "/faces/map/mapUpdate.xhtml")
public class MapUpdateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MapUpdateView.class);
	
	@Autowired
	private transient MapService	mapService;
	
	private Map					map;

	@PostConstruct
	public void init() {
		initBean();
		map = mapService.findMapById(getUser(), getParam("mapId"));
	}

	public String updateMap() {
		mapService.updateMap(getUser(), map);
		return "pretty:viewMapList";
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
