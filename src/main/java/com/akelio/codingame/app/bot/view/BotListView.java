package com.akelio.codingame.app.bot.view;

import java.util.List;
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
@ManagedBean(name = "botListView")
@URLMapping(id = "viewBotList", pattern = "/bot/list", viewId = "/faces/bot/botList.xhtml")
public class BotListView extends BaseView {

	private static final Logger			logger	= LoggerFactory.getLogger(BotListView.class);
	
	@Autowired
	private transient BotService	botService;

	private List<Bot>				botList;

	@PostConstruct
	public void init() {
		initBean();
		refresh();
	}

	private void refresh() {
		botList = botService.findAllMyBot(getUser());
	}
	
	public String deleteBot() {
		botService.deleteBot(getUser(), getParam("botId"));
		refresh();
		return "";
	}
	
	public List<Bot> getBotList() {
		return botList;
	}

	public void setBotList(List<Bot> botList) {
		this.botList = botList;
	}
	
	public Logger getLogger() {
		return logger;
	}
}
