package com.scf.skyware.main.service;

import com.scf.skyware.main.domain.User;

public interface MainService
{
	public User getUser(User u);
	public int regUser(User u);
	public User overlapIdCheck(User u);
	public int uptUser(User u);
	public int delUser(User u);
}
