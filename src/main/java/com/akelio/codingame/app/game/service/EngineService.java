package com.akelio.codingame.app.game.service;

import org.springframework.stereotype.Service;
import com.akelio.base.BaseService;
import com.akelio.codingame.util.UtilMaze;

@Service("engineService")
public class EngineService extends BaseService {

	public String buildMap(int x, int y) {
		return UtilMaze.buildMaze(x,y);
	}
}
