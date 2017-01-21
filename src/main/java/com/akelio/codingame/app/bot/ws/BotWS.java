package com.akelio.codingame.app.bot.ws;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.akelio.codingame.base.BaseWS;
import com.akelio.codingame.app.bot.service.BotService;
import com.akelio.codingame.app.bot.entity.Bot;

@RestController
public class BotWS extends BaseWS {
	// curl -i --user admin:admin -X GET http://localhost:8080/codingame/rest/v1/bot/22
	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X GET http://localhost:8080/codingame/rest/v1/bot/22
	
	@Autowired
	BotService	botService;
	
	// curl -i -X GET http://localhost:8080/codingame/rest/v1/bot/22
	@RequestMapping(value = "/bot/{botId}", method = RequestMethod.GET)
	public Bot findBotById(@PathVariable String botId) {
		Bot bot = botService.findBotById(getUser(), botId);
		return bot;
	}

	// curl -i -X GET http://localhost:8080/codingame/rest/v1/bot/list
	@RequestMapping(value = "/bot/list", method = RequestMethod.GET)
	public List<Bot> getBotList() {
		List<Bot> botList = botService.findAllBot(getUser());
		return botList;
	}
	
	// curl -i -H "Content-type: application/json" -X POST http://localhost:8080/codingame/rest/v1/bot -d "{\"botId\":botId, \"name\":name}"
	@RequestMapping(value = "/bot", method = RequestMethod.POST)
	public Bot createBot(@RequestBody Bot bot) {
		botService.createBot(getUser(), bot);
		return bot;
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -H "Content-type: application/json" -X PUT http://localhost:8080/codingame/rest/v1/bot/22 -d "{\"botId\":botId, \"name\":name, \"dateCreated\":dateCreated}"
	@RequestMapping(value = "/bot/{botId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updatTask(@PathVariable String botId, @RequestBody Bot bot) {
		botService.updateBot(getUser(), bot);
	}

	// curl -i -H "Authorization: token=a9163371-790e-45ef-b800-6452698ae443" -X DELETE http://localhost:8080/codingame/rest/v1/bot/22
	@RequestMapping(value = "/bot/{botId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteBot(@PathVariable String botId) {
		botService.deleteBot(getUser(), botId);
	}
}
