package com.akelio.codingame.app.bot.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.akelio.codingame.base.BaseView;
import com.akelio.codingame.app.bot.entity.Bot;
import com.akelio.codingame.app.bot.service.BotService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ViewScoped
@ManagedBean(name = "botDetailsView")
@URLMapping(id = "viewBotDetails", pattern = "/bot/#{/\\\\d+/botId}", viewId = "/faces/bot/botDetails.xhtml")
public class BotDetailsView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(BotDetailsView.class);
	
	@Autowired
	private transient BotService	botService;

	private Bot					bot;

	@PostConstruct
	public void init() {
		initBean();
		bot = botService.findBotById(getUser(), getParam("botId"));
	}

	public Bot getBot() {
		return bot;
	}

	public void setBot(Bot bot) {
		this.bot = bot;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
