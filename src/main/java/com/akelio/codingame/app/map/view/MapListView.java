package com.akelio.codingame.app.map.view;

import java.util.List;
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
@ManagedBean(name = "mapListView")
@URLMapping(id = "viewMapList", pattern = "/map/list", viewId = "/faces/map/mapList.xhtml")
public class MapListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(MapListView.class);
	
	@Autowired
	private transient MapService	mapService;

	private List<Map>				mapList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		mapList = mapService.findAllMap(getUser());
	}
	
	public String deleteMap() {
		mapService.deleteMap(getUser(), getParam("mapId"));
		refresh();
		return "";
	}
	
	public List<Map> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map> mapList) {
		this.mapList = mapList;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
