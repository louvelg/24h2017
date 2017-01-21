package com.akelio.codingame.app.bot.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;


public class Bot extends BaseEntity {
	
	private String botId;
	private String name;
	private String userId;
	private Date dateCreated;

	public String getId() {
		return botId;
	}

	public void setId(String id) {
		this.botId = id;
	}
	
	public String getBotId() {
		return botId;
	}
		
	public void setBotId(String botId) {
		this.botId = botId;
	}
	
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
		
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
		
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(botId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Bot))
			return false;
		return new EqualsBuilder().append(botId, ((Bot) obj).getBotId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(botId).toString();
	}
}