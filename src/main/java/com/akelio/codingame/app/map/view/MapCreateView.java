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
@ManagedBean(name = "mapCreateView")
@URLMapping(id = "viewMapCreate", pattern = "/map/create", viewId = "/faces/map/mapCreate.xhtml")
public class MapCreateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MapCreateView.class);
	
	@Autowired
	private transient MapService	mapService;

	private Map					map;

	@PostConstruct
	public void init() {
		initBean();
		map = new Map();
	}

	public String createMap() {
		
		mapService.createMap(getUser(), map);
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
