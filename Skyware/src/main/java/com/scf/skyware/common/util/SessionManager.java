package com.scf.skyware.common.util;

import javax.servlet.http.HttpSession;

public class SessionManager
{
	private static HttpSession session;

	public static HttpSession getSession()
	{
		return session;
	}

	public static void setSession(HttpSession session)
	{
		SessionManager.session = session;
	}
}
