package com.akelio.codingame.client.bots;

import java.util.Map;

public interface Bot {
	
	public enum DIR { N,S,W,E,I }

	public DIR computeDirection(Map data);
	public String getId();
}
