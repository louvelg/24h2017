package com.akelio.codingame.app.user.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.akelio.base.user.entity.BaseUser;

public class User extends BaseUser {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String email;
	private Date dateCreated;
	private Date dateUpdated;
	private Date dateDeleted;
	private Date dateDisable;

	public String getId() {
		return userId;
	}

	public void setId(String id) {
		this.userId = id;
	}
	
	public String getUserId() {
		return userId;
	}
		
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
		
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
		
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLogin() {
		return login;
	}
		
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
		
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
		
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}
		
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	public Date getDateDeleted() {
		return dateDeleted;
	}
		
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	
	public Date getDateDisable() {
		return dateDisable;
	}
		
	public void setDateDisable(Date dateDisable) {
		this.dateDisable = dateDisable;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User))
			return false;
		return new EqualsBuilder().append(userId, ((User) obj).getUserId()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(userId).append(firstName).append(lastName).append(login).toString();
	}
}