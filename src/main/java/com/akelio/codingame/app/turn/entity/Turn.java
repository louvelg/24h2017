package com.akelio.codingame.app.turn.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;

public class Turn extends BaseEntity {

	private String	turnId;
	private String	index;
	private String	gameId;
	private Date	dateCreated;
	private String	amountBot1;
	private String	amountBot2;
	private String	amountBot3;
	private String	amountBot4;
	private String	data;

	public String getId() {
		return turnId;
	}

	public void setId(String id) {
		this.turnId = id;
	}

	public String getTurnId() {
		return turnId;
	}

	public void setTurnId(String turnId) {
		this.turnId = turnId;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAmountBot1() {
		return amountBot1;
	}

	public void setAmountBot1(String amountBot1) {
		this.amountBot1 = amountBot1;
	}

	public String getAmountBot2() {
		return amountBot2;
	}

	public void setAmountBot2(String amountBot2) {
		this.amountBot2 = amountBot2;
	}

	public String getAmountBot3() {
		return amountBot3;
	}

	public void setAmountBot3(String amountBot3) {
		this.amountBot3 = amountBot3;
	}

	public String getAmountBot4() {
		return amountBot4;
	}

	public void setAmountBot4(String amountBot4) {
		this.amountBot4 = amountBot4;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(turnId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof Turn)) return false;
		return new EqualsBuilder().append(turnId, ((Turn) obj).getTurnId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(turnId).toString();
	}
}
