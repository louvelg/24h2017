package com.akelio.codingame.app.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.user.service.BaseUserService;
import com.akelio.codingame.app.user.dao.UserDAO;
import com.akelio.codingame.app.user.entity.User;


@Service("userService")
public class UserService extends BaseUserService<User> {

	@Autowired
	UserDAO	userDAO;
	
	public User findUserById(User currentUser, String userId) {
		return userDAO.findUserById(userId);
	}

	public List<User> findAllUser(User currentUser) {
		return userDAO.findAllUser();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createUser(User currentUser, User user) {
		userDAO.createUser(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUser(User currentUser, User user) {
		userDAO.updateUser(user);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteUser(User currentUser, String userId) {
		userDAO.deleteUser(userId);
	}
	
	
	public User findUserDeletedById(User user, String userId) {
		return userDAO.findUserDeletedById(user.getTenantId(), userId);
	}

	public User findUserDisabledById(User user, String userId) {
		return userDAO.findUserDisabledById(user.getTenantId(), userId);
	}

	public User findUserByLogin(String login) {
		return userDAO.findUserByLogin(login);
	}

	public User findUserByLogin(String tenantId, String login) {
		return userDAO.findUserByLogin(tenantId, login);
	}

	public User findUserNotDisableByLogin(String login) {
		return userDAO.findUserNotDisableByLogin(login);
	}

	public List<User> findAllUserDisable(User user) {
		return userDAO.findAllUserDisable(user.getTenantId());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserPassword(String tenantId, String id, String password) {
		userDAO.updateUserPassword(tenantId, id, password);
	}

	public List<User> findAllUsers(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAllUsersByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAllUsersByMail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAllUsersByMail(User currentUser, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAllUsersWithRole(User currentUser, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAllUsersDisable(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUserPassword(User currentUser, String userId, String password) {
		// TODO Auto-generated method stub

	}

	public void updateUserLocale(User currentUser, String userId, String locale) {
		// TODO Auto-generated method stub

	}

	public void disableUser(User currentUser, User user) {
		// TODO Auto-generated method stub

	}

	public void enableUser(User currentUser, User user) {
		// TODO Auto-generated method stub

	}
	
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createUser(User newUser) {
		// TODO Auto-generated method stub
	}

	public void updateUserPassword(String userId, String password) {
		// TODO Auto-generated method stub
	}
	
	
}
