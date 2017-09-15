package com.scf.skyware.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.test.dao.TestDAO;
import com.scf.skyware.test.domain.Test;

@Service
public class TestServiceImpl implements TestService
{
	private TestDAO testDao;
	
	@Autowired
	public void setTestDao(TestDAO testDao)
	{
		this.testDao = testDao;
	}
	
	@Override
	public List<Test> getTestListAll(Test t)
	{
		return testDao.getTestListAll(t);
	}
}
