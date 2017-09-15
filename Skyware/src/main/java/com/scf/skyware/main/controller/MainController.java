package com.scf.skyware.main.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scf.skyware.common.util.AdminLoginCheck;
import com.scf.skyware.common.util.Base64;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.main.service.MainService;
import com.scf.skyware.manage.domain.Code;
import com.scf.skyware.manage.service.ManageService;

@Controller
public class MainController
{
	private MainService mainService;
	private ManageService manageService;
	
	@Autowired
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}
	
	@Autowired
	public void setManageService(ManageService manageService)
	{
		this.manageService = manageService;
	}
	
	@RequestMapping(value={"/", "/index", "/home"})
	public String index(Model model, HttpServletRequest request, HttpSession session) throws Exception
	{
//		boolean isLogin = Boolean.valueOf(session.getAttribute("isLogin").toString());
//		
//		if (isLogin)
//		{
//			session.setAttribute("userNm", "nonmem");
//			session.setAttribute("userId", "nonmem");
//			session.setAttribute("isLogin", false);
//		}
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				// id저장 체크했을경우
				if ("userId".equals(cookies[i].getName()))
				{
					model.addAttribute("saveId", cookies[i].getValue());
				}
			}
		}
		
		/*KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
 
		request.getSession().setAttribute("_RSA_WEB_Key_", privateKey);
		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
 
		model.addAttribute("RSAModulus", publicKeyModulus);
		model.addAttribute("RSAExponent", publicKeyExponent);*/
		
		return "main/home";
	}
	
	@RequestMapping("/header")
	public String header(Model model)
	{
		Code c = new Code();
		c.setCodeDiv("MNU01");
		List<Code> codeList = manageService.getCodeList(c);
		
		model.addAttribute("codeList", codeList);
		
		return "layouts/header";
	}
	
	@RequestMapping("/leftMenu")
	public String leftMenu(Model model)
	{
		Code c = new Code();
		c.setCodeDiv("MNU01");
		List<Code> codeList = manageService.getCodeList(c);
		
		model.addAttribute("codeList", codeList);
		
		return "layouts/leftMenu";
	}
	
	@RequestMapping("/footer")
	public String footer(Model model)
	{
		return "layouts/footer";
	}
	
	@RequestMapping(value={"/LoginAction"}, method = RequestMethod.POST)
	public String loginAction(Model model, HttpSession session, HttpServletResponse response, @ModelAttribute("user") User u)
	{
		User user = mainService.getUser(u);
		String path = null;
		int result = 0;
		
		if (user == null)
		{
			path = "redirect:/home";
			result = AdminLoginCheck.ID_NOT_FOUND;
		}
		else if (!Base64.base64Encode(u.getUserPw()).equals(user.getUserPw()))
		{
			path = "redirect:/home";
			result = AdminLoginCheck.PASSWORD_MISMATCH;
		}
		else if ("N".equals(user.getUseYn()))
		{
			path = "redirect:/home";
			result = AdminLoginCheck.ACCESS_DENIED;
		}
		else
		{	
			session.setAttribute("userNm", user.getUserNm());
			session.setAttribute("userId", u.getUserId());
			session.setAttribute("isLogin", true);
			path = "redirect:/manage/code/codeRegView";
			result = AdminLoginCheck.LOGIN_SUCCESS;
		}
		
		if (u.getSaveId() != null)
		{
			if (u.getSaveId().equals("save"))
			{
				Cookie userInfo = new Cookie("userId", u.getUserId());
	
				userInfo.setMaxAge(24 * 60 * 60 * 5);
				userInfo.setPath("/");
				
				response.addCookie(userInfo);
			}
		}
		else
		{
			Cookie userInfo = new Cookie("userId", null);

			userInfo.setMaxAge(0);
			userInfo.setPath("/");
			
			response.addCookie(userInfo);
		}
		
		
		model.addAttribute("result", result);
		
		return path;
	}
	
	@RequestMapping("/LogoutAction")
	public String logout(Model model, HttpSession session)
	{
		session.setAttribute("userNm", "nonmem");
		session.setAttribute("userId", "nonmem");
		session.setAttribute("isLogin", false);
		
		return "redirect:/";
	}
	
	@RequestMapping("/popup/regUserView")
	public String joinUserView(Model model, HttpServletRequest request)
	{
		return "popup/regUserView";
	}
	
	@RequestMapping("/ajax/regUserAction")
	public String joinUserAction(Model model, HttpSession session, @ModelAttribute("user") User u)
	{
		/*try
		{
			PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");
			String decPw = RSA.decryptRsa(privateKey, u.getUserPw());
			System.out.println("Pw : " + u.getUserPw());
			System.out.println("devPw" + decPw);
			u.setUserPw(SecurityUtil.getSHA256(decPw));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}*/
		
		u.setUserPw(Base64.base64Encode(u.getUserPw()));
		
		int result = mainService.regUser(u);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxRegUser";
	}
	
	@RequestMapping("/ajax/overlapIdCheck")
	public String overlapIdCheck(Model model, @ModelAttribute("user") User u)
	{
		String userId = u.getUserId();
		User user = mainService.getUser(u);
		boolean result = false;
		
		if (user != null)
		{
			if (userId.equals(user.getUserId()))
			{
				result = true;
			}
		}
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxOverlapIdCheck";
	}
	
	@RequestMapping("/popup/jusoPopup")
	public String josoPopup(Model model)
	{
		return "popup/jusoPopup";
	}
}
