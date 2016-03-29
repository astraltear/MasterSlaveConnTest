package com.audien.db.aop;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ConnectService")
public class ConnectService {
	
	@Inject
	ConnectDAO connectDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectService.class);
	 
	
	  @Transactional(readOnly=true)
	  @DataSource(DataSourceType.SLAVE)
	  public void listMemberSlave() throws DataAccessException {
		  
		  List list = connectDAO.readAll();
		  
		  for (int i = 0; i < list.size(); i++) {
			MemberVO memberVO = (MemberVO) list.get(i);
			logger.info(memberVO.toString());
		}
	  }
	  
	  @Transactional(readOnly=true)
	  @DataSource(DataSourceType.MASTER)
	  public void listMemberMaster() throws DataAccessException {
		  
		  List list = connectDAO.readAll();
		  
		  for (int i = 0; i < list.size(); i++) {
			MemberVO memberVO = (MemberVO) list.get(i);
			logger.info(memberVO.toString());
		}
	  }
}
