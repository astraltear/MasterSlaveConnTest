package com.audien.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RoutingDataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceTest.class);

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationCtx.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		logger.info("RoutingDataSourceTest.dataSource:"+dataSource.toString());

	}

}
