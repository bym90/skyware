package com.scf.skyware.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.scf.skyware.manage.domain.AdminAuth;
import com.scf.skyware.manage.service.ManageService;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter
{
//	@Autowired ManageService manageService;
	private ManageService manageService;
	
	@Autowired
	public void setManageService(ManageService manageService)
	{
		this.manageService = manageService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	{
		try
		{
			AdminAuth admin = new AdminAuth();
			System.err.println(request.getSession().getAttribute("userId"));
			admin.setUserId(request.getSession().getAttribute("userId").toString());
			System.err.println(admin.toString());
			
			AdminAuth admin2 = new AdminAuth();
			admin2 = manageService.getAdminChk(admin);
			
			System.err.println(manageService.getAdminChk(admin));
			
//			admin = manageService.getAdminChk(admin);
			
			System.err.println(admin.toString());
			
			return false;
		}
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		}
		
		return true;
	}
}
