package com.scf.skyware.common.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCount implements HttpSessionListener
{
	public static int count;

	public static int getCount()
	{
		return count;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		count++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		count--;
		if (count < 0)
		{
			count = 0;
		}
	}
}
