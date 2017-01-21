package com.akelio.codingame.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = "com.akelio", excludeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { org.springframework.stereotype.Controller.class,
		org.springframework.web.bind.annotation.RestController.class }) })
@PropertySources(value = { @PropertySource("classpath:application.properties"), @PropertySource("classpath:env.properties") })
@ImportResource({ "classpath:applicationContext.xml" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationConfig {
	@Autowired
	Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource bean = new DataSource();
		bean.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		bean.setUrl(env.getProperty("spring.datasource.url"));
		bean.setUsername(env.getProperty("spring.datasource.username"));
		bean.setPassword(env.getProperty("spring.datasource.password"));
		bean.setTestOnBorrow(true);
		bean.setValidationQuery("Select 1");
		return bean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	// USE TO @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
