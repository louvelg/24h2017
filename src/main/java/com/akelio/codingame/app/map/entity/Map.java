package com.akelio.codingame.app.map.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.BaseEntity;


public class Map extends BaseEntity {
	
	private String mapId;
	private String name;
	private String width;
	private String height;
	private String data;
	private String nbSomme;
	private String maxSomme;
	private String minSomme;
	private Date dateCreated;

	
	public String getId() {
		return mapId;
	}

	public void setId(String id) {
		this.mapId = id;
	}
	
	public String getMapId() {
		return mapId;
	}
		
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWidth() {
		return width;
	}
		
	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getHeight() {
		return height;
	}
		
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getData() {
		return data;
	}
		
	public void setData(String data) {
		this.data = data;
	}
	
	public String getNbSomme() {
		return nbSomme;
	}
		
	public void setNbSomme(String nbSomme) {
		this.nbSomme = nbSomme;
	}
	
	public String getMaxSomme() {
		return maxSomme;
	}
		
	public void setMaxSomme(String maxSomme) {
		this.maxSomme = maxSomme;
	}
	
	public String getMinSomme() {
		return minSomme;
	}
		
	public void setMinSomme(String minSomme) {
		this.minSomme = minSomme;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
		
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mapId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Map))
			return false;
		return new EqualsBuilder().append(mapId, ((Map) obj).getMapId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(mapId).toString();
	}
	
	
	
	public int getHeightAsInt() {
		return Integer.parseInt(height);
	}
	public int getWidthAsInt() {
		return Integer.parseInt(width);
	}
	public int getMaxSommeAsInt() {
		return Integer.parseInt(maxSomme);
	}
	public int getMinSommeAsInt() {
		return Integer.parseInt(minSomme);
	}
}