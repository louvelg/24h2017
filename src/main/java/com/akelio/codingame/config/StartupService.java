package com.akelio.codingame.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StartupService {
	private static final Logger	logger	= LoggerFactory.getLogger(StartupService.class);

	@Autowired
	private ApplicationContext	appContext;

	@PostConstruct
	public void startApp() {
		logger.info("Startup Application");
		Flyway flyway = new Flyway();
		DataSource ds = (DataSource) appContext.getBean("dataSource");
		flyway.setBaselineOnMigrate(true);
		flyway.setOutOfOrder(true);
		flyway.setDataSource(ds);
		
		//flyway.migrate();
		flyway.baseline(); //A ne lancer qu'une fois, puis décommenter le migrate et supprimer cette ligne
	}

}
