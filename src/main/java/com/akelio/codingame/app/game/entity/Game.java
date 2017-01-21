package com.akelio.codingame.app.game.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;
import com.akelio.codingame.app.map.entity.Map;
import com.akelio.codingame.app.turn.entity.Turn;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Game extends BaseEntity {

	private String		gameId;
	private String		name;
	private String		mapId;
	private String		bot1Id;
	private String		bot2Id;
	private String		bot3Id;
	private String		bot4Id;
	private Date		dateCreated;
	private String		status;
	private String		currentBot;
	private Map			map;

	private List<Turn>	turnList;

	public Game() {
		turnList = new ArrayList<>();
	}

	public boolean isPending() {
		if (bot1Id == null) return true;
		if (bot2Id == null) return true;
		if (bot3Id == null) return true;
		if (bot4Id == null) return true;

		return false;
	}

	public int setNextBotId(String botId) {
		if (bot1Id == null) {
			bot1Id = botId;
			return 1;
		}
		if (bot2Id == null) {
			bot2Id = botId;
			return 2;
		}
		if (bot3Id == null) {
			bot3Id = botId;
			return 3;
		}
		if (bot4Id == null) {
			bot4Id = botId;
			return 4;
		}
		return -1;
	}

	public String getBotIdForName(String name) {
		if (name.equals("A")) return bot1Id;
		if (name.equals("B")) return bot2Id;
		if (name.equals("C")) return bot3Id;
		if (name.equals("D")) return bot4Id;

		return null;
	}

	public String getId() {
		return gameId;
	}

	public void setId(String id) {
		this.gameId = id;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public String getBot1Id() {
		return bot1Id;
	}

	public void setBot1Id(String bot1Id) {
		this.bot1Id = bot1Id;
	}

	public String getBot2Id() {
		return bot2Id;
	}

	public void setBot2Id(String bot2Id) {
		this.bot2Id = bot2Id;
	}

	public String getBot3Id() {
		return bot3Id;
	}

	public void setBot3Id(String bot3Id) {
		this.bot3Id = bot3Id;
	}

	public String getBot4Id() {
		return bot4Id;
	}

	public void setBot4Id(String bot4Id) {
		this.bot4Id = bot4Id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonIgnore
	public List<Turn> getTurnList() {
		return turnList;
	}

	public void setTurnList(List<Turn> turnList) {
		this.turnList = turnList;
	}

	public boolean hasTurns() {
		return turnList != null && turnList.size() > 0;
	}

	public Turn getLastTurn() {
		if (turnList == null || turnList.isEmpty()) return null;
		return turnList.get(turnList.size() - 1);
	}

	public String getCurrentBot() {
		return currentBot;
	}

	public void setCurrentBot(String currentBot) {
		this.currentBot = currentBot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gameId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof Game)) return false;
		return new EqualsBuilder().append(gameId, ((Game) obj).getGameId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(gameId).toString();
	}
}
