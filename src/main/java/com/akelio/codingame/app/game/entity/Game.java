package com.akelio.codingame.app.game.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;


public class Game extends BaseEntity {
	
	private String gameId;
	private String name;
	private String mapId;
	private String bot1Id;
	private String bot2Id;
	private String bot3Id;
	private String bot4Id;
	private Date dateCreated;

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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gameId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Game))
			return false;
		return new EqualsBuilder().append(gameId, ((Game) obj).getGameId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(gameId).toString();
	}
}