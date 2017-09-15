package com.scf.skyware.main.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.main.domain.User;

@Repository
public class MainDAOImpl implements MainDAO
{
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession)
	{
		this.sqlSession = sqlSession;
	}
	
	@Override
	public User getUser(User u)
	{
		return sqlSession.selectOne("Main_getUser", u);
	}
	
	@Override
	public int regUser(User u)
	{
		return sqlSession.insert("Main_regUser", u);
	}
	
	@Override
	public User overlapIdCheck(User u)
	{
		return sqlSession.selectOne("Main_overlapIdCheck", u);
	}

	@Override
	public int uptUser(User u)
	{
		return sqlSession.update("Main_uptUser", u);
	}

	@Override
	public int delUser(User u)
	{
		return sqlSession.update("Main_delUser", u);
	}
}
