package com.audien.db.aop;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.astraltear.springpilot.mapper.MemberMapper";
	
	public List readAll() {
		return sqlSession.selectList(namespace+".readMemberAll");
	}

}
