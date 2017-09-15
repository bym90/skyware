package com.scf.skyware.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	{
		try
		{ //logininfo 세션값이 널일경우
			
//			System.err.println("isLogin : " + request.getSession().getAttribute("isLogin"));
//			System.err.println("userId : " + request.getSession().getAttribute("userId"));
//			System.err.println("userNm : " + request.getSession().getAttribute("userNm"));
			
			if(request.getSession().getAttribute("isLogin") == null || Boolean.valueOf(request.getSession().getAttribute("isLogin").toString()) == false)
			{ //로그인페이지로 redirect
//				PrintWriter out = response.getWriter();
				
				response.setContentType("text/html;charset=UTF-8");
				
//				out.print("<html>");
//				out.print("	<script>");
//				out.print("		alert('세션이 종료되었습니다. 다시 로그인 해주세요.')");
//				out.print("	</script>");
//				out.print("</html>");
				
				response.sendRedirect("/home?chk=true");
				
//				out.flush();
//				out.close();
				
//				RequestDispatcher rd = request.getRequestDispatcher("/index?chk=true");
//				rd.forward(request, response);
				
				return false;
			} 
		}
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		} //널이 아니면 정상적으로 컨트롤러 호출
		return true;
	}
}
