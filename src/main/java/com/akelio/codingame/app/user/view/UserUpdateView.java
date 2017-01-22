package com.akelio.codingame.app.user.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.app.user.entity.User;
import com.akelio.codingame.app.user.service.UserService;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.jsf.Jsf;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "userUpdateView")
@URLMapping(id = "viewUserUpdate", pattern = "/user/update/#{/\\\\d+/userId}", viewId = "/faces/user/userUpdate.xhtml")
public class UserUpdateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(UserUpdateView.class);
	
	@Autowired
	private transient UserService	userService;
	
	private User					userTmp;

	@PostConstruct
	public void init() {
		initBean();
		userTmp = userService.findUserById(getUser(), getParam("userId"));
	}

	public String updateUser() {
		userService.updateUser(getUser(), userTmp);
		getVisit().setUser(userTmp);
		Jsf.info("Votre profil a été enregistré.");
		return "";
	}

	public User getUserTmp() {
		return userTmp;
	}

	public void setUserTmp(User userTmp) {
		this.userTmp = userTmp;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
