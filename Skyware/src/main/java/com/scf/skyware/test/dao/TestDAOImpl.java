package com.scf.skyware.test.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.test.domain.Test;

@Repository
public class TestDAOImpl implements TestDAO
{
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession)
	{
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Test> getTestListAll(Test t)
	{
		return sqlSession.selectList("getTestList", t);
	}
}
