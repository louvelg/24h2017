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
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "userCreateView")
@URLMapping(id = "viewUserCreate", pattern = "/user/create", viewId = "/faces/user/userCreate.xhtml")
public class UserCreateView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(UserCreateView.class);
	
	@Autowired
	private transient UserService	userService;

	private User					userTmp;

	@PostConstruct
	public void init() {
		initBean();
		userTmp = new User();
	}

	public String createUser() {
		
		userService.createUser(getUser(), userTmp);
		return "pretty:viewUserList";
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
	
	public User getUser() {
		return null;
	}

}
