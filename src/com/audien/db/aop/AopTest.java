package com.audien.db.aop;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class AopTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AopTest.class);


	public static void main(String[] args) throws DataAccessException, SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationCtxAOP.xml");
		ConnectService connectService = (ConnectService) context.getBean("ConnectService");
		
		logger.info("---------- MASTER START ---------------------");
		connectService.listMemberMaster();
		logger.info("---------- MASTER END ---------------------");
		
		logger.info("---------- SLAVE START ---------------------");
		connectService.listMemberSlave();
		logger.info("---------- SLAVE END ---------------------");
		
	}

}
