package com.akelio.codingame.common.view;

import java.io.Serializable;
import com.akelio.codingame.app.user.entity.User;

public class VisitBean implements Serializable {
	private User	user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
