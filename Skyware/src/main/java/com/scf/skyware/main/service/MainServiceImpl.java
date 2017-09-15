package com.scf.skyware.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.main.dao.MainDAO;
import com.scf.skyware.main.domain.User;

@Service
public class MainServiceImpl implements MainService
{
	private MainDAO mainDAO;
	
	@Autowired
	public void setMainDAO(MainDAO mainDAO)
	{
		this.mainDAO = mainDAO;
	}
	
	@Override
	public User getUser(User u)
	{
		return mainDAO.getUser(u);
	}
	
	@Override
	public int regUser(User u)
	{
		return mainDAO.regUser(u);
	}
	
	@Override
	public User overlapIdCheck(User u)
	{
		return mainDAO.overlapIdCheck(u);
	}

	@Override
	public int uptUser(User u)
	{
		return mainDAO.uptUser(u);
	}

	@Override
	public int delUser(User u)
	{
		return mainDAO.delUser(u);
	}
}
