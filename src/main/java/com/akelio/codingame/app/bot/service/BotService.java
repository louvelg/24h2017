package com.akelio.codingame.app.bot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.akelio.base.BaseService;

import com.akelio.codingame.app.bot.dao.BotDAO;
import com.akelio.codingame.app.bot.entity.Bot;
import com.akelio.codingame.app.user.entity.User;

@Service("botService")
public class BotService extends BaseService {

	@Autowired
	BotDAO	botDAO;
	
	public Bot findBotById(User currentUser, String botId) {
		return botDAO.findBotById(botId);
	}

	public List<Bot> findAllBot(User currentUser) {
		return botDAO.findAllBot();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createBot(User currentUser, Bot bot) {
		bot.setUserId(currentUser.getUserId());
		botDAO.createBot(bot);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateBot(User currentUser, Bot bot) {
		botDAO.updateBot(bot);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteBot(User currentUser, String botId) {
		botDAO.deleteBot(botId);
	}
	
	
}
