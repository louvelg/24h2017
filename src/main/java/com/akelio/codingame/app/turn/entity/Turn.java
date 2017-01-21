package com.akelio.codingame.app.turn.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;


public class Turn extends BaseEntity {
	
	private String turnId;
	private String index;
	private String gameId;
	private Date dateCreated;
	private String xBot1;
	private String yBot1;
	private String xBot2;
	private String yBot2;
	private String xBot3;
	private String yBot3;
	private String xBot4;
	private String yBot4;
	private String amountBot1;
	private String amountBot2;
	private String amountBot3;
	private String amountBot4;

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
	
	public String getXBot1() {
		return xBot1;
	}
		
	public void setXBot1(String xBot1) {
		this.xBot1 = xBot1;
	}
	
	public String getYBot1() {
		return yBot1;
	}
		
	public void setYBot1(String yBot1) {
		this.yBot1 = yBot1;
	}
	
	public String getXBot2() {
		return xBot2;
	}
		
	public void setXBot2(String xBot2) {
		this.xBot2 = xBot2;
	}
	
	public String getYBot2() {
		return yBot2;
	}
		
	public void setYBot2(String yBot2) {
		this.yBot2 = yBot2;
	}
	
	public String getXBot3() {
		return xBot3;
	}
		
	public void setXBot3(String xBot3) {
		this.xBot3 = xBot3;
	}
	
	public String getYBot3() {
		return yBot3;
	}
		
	public void setYBot3(String yBot3) {
		this.yBot3 = yBot3;
	}
	
	public String getXBot4() {
		return xBot4;
	}
		
	public void setXBot4(String xBot4) {
		this.xBot4 = xBot4;
	}
	
	public String getYBot4() {
		return yBot4;
	}
		
	public void setYBot4(String yBot4) {
		this.yBot4 = yBot4;
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(turnId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Turn))
			return false;
		return new EqualsBuilder().append(turnId, ((Turn) obj).getTurnId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(turnId).toString();
	}
}