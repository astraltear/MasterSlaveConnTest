package com.audien.db.routesource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class RoutingDataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceTest.class);
	
	private static ApplicationContext context = null;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:applicationCtx.xml");
		readQuery();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly = true)
	public static void readQuery() {
		DataSource dataSource = (DataSource) context.getBean("lastDataSource");
		logger.info("RoutingDataSourceTest.dataSource>>>>>>>>>>>>>>>>>>>>>>>>"+dataSource.toString());
		
		try {
			Connection connection =  dataSource.getConnection();
			
//			connection.setReadOnly(true);
			DatabaseMetaData dmd = connection.getMetaData();
			String url = dmd.getURL();
			
			logger.info(url );
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
