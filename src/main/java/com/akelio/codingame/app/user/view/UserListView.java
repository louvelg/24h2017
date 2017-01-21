package com.akelio.codingame.app.user.view;

import java.util.List;
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
@ManagedBean(name = "userListView")
@URLMapping(id = "viewUserList", pattern = "/user/list", viewId = "/faces/user/userList.xhtml")
public class UserListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(UserListView.class);
	
	@Autowired
	private transient UserService	userService;

	private List<User>				userList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		userList = userService.findAllUser(getUser());
	}
	
	public String deleteUser() {
		userService.deleteUser(getUser(), getParam("userId"));
		refresh();
		return "";
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
