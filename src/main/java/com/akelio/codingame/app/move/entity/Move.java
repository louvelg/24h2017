package com.akelio.codingame.app.move.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;


public class Move extends BaseEntity {
	
	private String moveId;
	private String gameId;
	private String turnId;
	private String indice;
	private String botId;
	private String botName;
	private String direction;
	private Date dateCreated;

	public String getId() {
		return moveId;
	}

	public void setId(String id) {
		this.moveId = id;
	}
	
	public String getMoveId() {
		return moveId;
	}
		
	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}
	
	public String getGameId() {
		return gameId;
	}
		
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public String getTurnId() {
		return turnId;
	}
		
	public void setTurnId(String turnId) {
		this.turnId = turnId;
	}
	
	public String getIndice() {
		return indice;
	}
		
	public void setIndice(String indice) {
		this.indice = indice;
	}
	
	public String getBotId() {
		return botId;
	}
		
	public void setBotId(String botId) {
		this.botId = botId;
	}
	
	public String getBotName() {
		return botName;
	}
		
	public void setBotName(String botName) {
		this.botName = botName;
	}
	
	public String getDirection() {
		return direction;
	}
		
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
		
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(moveId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Move))
			return false;
		return new EqualsBuilder().append(moveId, ((Move) obj).getMoveId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(moveId).toString();
	}
}