package com.akelio.codingame.base;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.akelio.codingame.app.user.entity.User;
import com.akelio.codingame.app.user.service.UserService;

public abstract class BaseWS implements Serializable {

	@Autowired
	protected UserService		userService;

	private static final long	serialVersionUID	= 1L;

	public ResourceBundle getBundle(Locale locale) {
		return ResourceBundle.getBundle("com.akelio.messages", locale);
	}

	public String getEnv(String key) {
		String message;
		try {
			message = ResourceBundle.getBundle("env").getString(key);
		} catch (java.util.MissingResourceException mre) {
//			logger.warn("Missing key for '" + key + "' in env.properties");
			return "???" + key + "???";
		}
		return message;
	}

	public String getFormattedMessage(String locale, String key, String param) {
		String message;
		try {
			message = getBundle(new Locale(locale)).getString(key);
			message = MessageFormat.format(message, param);
		} catch (java.util.MissingResourceException mre) {
//			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public User getUser() {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

}
