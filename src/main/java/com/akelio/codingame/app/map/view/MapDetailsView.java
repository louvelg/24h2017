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
@ManagedBean(name = "mapDetailsView")
@URLMapping(id = "viewMapDetails", pattern = "/map/#{/\\\\d+/mapId}", viewId = "/faces/map/mapDetails.xhtml")
public class MapDetailsView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MapDetailsView.class);
	
	@Autowired
	private transient MapService	mapService;

	private Map					map;

	@PostConstruct
	public void init() {
		initBean();
		map = mapService.findMapById(getUser(), getParam("mapId"));
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
