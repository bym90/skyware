package com.scf.skyware.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scf.skyware.common.util.Base64;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.main.service.MainService;
import com.scf.skyware.manage.domain.AdminAuth;
import com.scf.skyware.manage.domain.Code;
import com.scf.skyware.manage.service.ManageService;

@Controller
public class ManageController
{
	private ManageService manageService;
	private MainService mainService;

	@Autowired
	public void setManageService(ManageService manageService)
	{
		this.manageService = manageService;
	}
	
	@Autowired
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@RequestMapping("/manage/code/codeRegView")
	public String codeRegView(Model model)
	{
		Code c = new Code();
		c.setCodeDiv("BAS");
		List<Code> baseCodeList = manageService.getCodeList(c);

		c.setCodeDiv("USE01");
		List<Code> useCodeList = manageService.getCodeList(c);

		model.addAttribute("baseCodeList", baseCodeList);
		model.addAttribute("useCodeList", useCodeList);

		return "manage/code/codeRegView";
	}
	
	@RequestMapping("/popup/basecodeRegView")
	public String basecodeRegView(Model model)
	{
		return "popup/regBaseCodeView";
	}
	
	@RequestMapping("/manage/code/basecodeRegAction")
	public String basecodeRegAction(Model model, @ModelAttribute("code") Code c)
	{
		c.setCodeDiv("BAS");
		c.setCodeDivNm("BASECODE");
		
		model.addAttribute("result", manageService.regBaseCode(c));
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/code/codeDuplCheck")
	public String codeDuplCheck(Model model, @ModelAttribute("code") Code c)
	{
		boolean result = false;
		
		if (manageService.codeDuplCheck(c) > 0)
		{
			result = true;
		}
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}

	@RequestMapping("/manage/code/codeList")
	public String codeList(Model model, @ModelAttribute("code") Code c)
	{
		List<Code> codeList = manageService.getCodeList(c);
		c.setCodeDiv("USE01");
		List<Code> useCodeList = manageService.getCodeList(c);

		model.addAttribute("codeList", codeList);
		model.addAttribute("useCodeList", useCodeList);

		return "manage/code/ajaxCodeList";
	}

	@RequestMapping("/manage/code/codeRegAction")
	public String codeRegAction(Model model, @ModelAttribute("code") Code c)
	{
		c.setCodeId(c.getCodeDiv() + manageService.getMaxNamingCodeSeq(c.getCodeDiv()));
		c.setCodeSort(manageService.getMaxNamingCodeSort(c.getCodeDiv()));
		c.setCodeDescription(c.getCodeDivNm() + "_" + c.getCodeNm());

		int result = manageService.regNamingCode(c);

		model.addAttribute("result", result);

		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/code/codeUptView")
	public String codeUptView(Model model, @ModelAttribute("code") Code c)
	{
		int result = manageService.regNamingCode(c);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/code/codeUptAction")
	public String codeUptAction(Model model, @ModelAttribute("code") Code c)
	{
		int result = manageService.regNamingCode(c);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/code/codeDelAction")
	public String codeDelAction(Model model, @ModelAttribute("code") Code c)
	{
		int result = manageService.delNamingCode(c);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}

	@RequestMapping("/manage/user/userManageView")
	public String userManageView(Model model, HttpServletRequest request)
	{
		Code c = new Code();
		c.setCodeDiv("SCH01");
		List<Code> selectComboList1 = manageService.getCodeList(c);
		
		c.setCodeDiv("SCH02");
		List<Code> selectComboList2 = manageService.getCodeList(c);
		
		model.addAttribute("selectComboList1", selectComboList1);
		model.addAttribute("selectComboList2", selectComboList2);

		return "manage/user/userManageView";
	}

	@RequestMapping("/manage/user/userManageList")
	public String userManageList(Model model, HttpServletRequest request)//, @ModelAttribute("user") User u)
	{
		int curPage = request.getParameter("curPage") == null ? 1 : "".equals(request.getParameter("curPage")) ? 1 : Integer.parseInt(request.getParameter("curPage").toString());
		int pageSize = request.getParameter("pageSize") == null ? 10 :Integer.parseInt(request.getParameter("pageSize").toString());
		String schState = request.getParameter("schState") == null ? "" : request.getParameter("schState").toString();
		String schType = request.getParameter("schType") == null ? "" : request.getParameter("schType").toString();
		String schText = request.getParameter("schText") == null ? "" : request.getParameter("schText").toString();
		
		User u = new User();
		u.setCurPage((curPage - 1) * 10);
		u.setPageSize(pageSize);
		u.setSchState(schState);
		u.setSchType(schType);
		u.setSchText(schText);
		

		List<User> userList = manageService.getUserList(u);

		int totalPage = manageService.getUserListCount(u);
		model.addAttribute("totalCnt", totalPage);
		
		int pageCount = totalPage / pageSize + (totalPage % pageSize == 0 ? 0 : 1);
		int sPage = (curPage / pageSize) * pageSize + 1 - (curPage % pageSize == 0 ? pageSize : 0);
		int ePage = sPage + pageSize - 1;
		
		if (ePage > pageCount)
		{
			ePage = pageCount;
		}
		
		model.addAttribute("userList", userList);
		model.addAttribute("sPage", sPage);
		model.addAttribute("ePage", ePage);
		model.addAttribute("curPage", curPage);

		return "manage/user/userManageList";
	}
	
	@RequestMapping("/popup/regUserManageView")
	public String regUserManageView(Model model, HttpServletRequest request)
	{
//		if (request.getParameter("userId") != null)
//		{
//			User u = new User();
//			u.setUserId(request.getParameter("userId"));
//			
//			u = mainService.getUser(u);
//			
//			model.addAttribute("user", u);
//		}
		
		return "popup/regUserManageView";
	}
	
	@RequestMapping("/popup/regUserDetailView")
	public String regUserDetailView(Model model, HttpServletRequest request)
	{
		if (request.getParameter("userId") != null)
		{
			User u = new User();
			
			u.setUserId(request.getParameter("userId"));
			
			u = mainService.getUser(u);
			
			model.addAttribute("user", u);
		}
		
		return "popup/regUserDetailView";
	}
	
	@RequestMapping("/ajax/uptUserAction")
	public String uptUserAction(Model model, @ModelAttribute("user") User u)
	{
		if (u.getUserPw() != null)
		{
			u.setUserPw(Base64.base64Encode(u.getUserPw()));
		}
		
		int result = mainService.uptUser(u);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/ajax/delUserAction")
	public String delUserAction(Model model, @ModelAttribute("user") User u)
	{
		int result = mainService.delUser(u);
		
		model.addAttribute("result", result);
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/auth/authManage")
	public String authManage(Model model, HttpServletRequest request)
	{
		int curPage = request.getParameter("curPage") == null ? 1 : "".equals(request.getParameter("curPage")) ? 1 : Integer.parseInt(request.getParameter("curPage").toString());
		int pageSize = request.getParameter("pageSize") == null ? 10 :Integer.parseInt(request.getParameter("pageSize").toString());

		AdminAuth auth = new AdminAuth();
		auth.setCurPage((curPage - 1) * 10);
		auth.setPageSize(pageSize);
		
		List<AdminAuth> adminList = manageService.getAdminList(auth);
		
		int totalPage = manageService.getAdminListCount(auth);
		model.addAttribute("totalCnt", totalPage);
		
		int pageCount = totalPage / pageSize + (totalPage % pageSize == 0 ? 0 : 1);
		int sPage = (curPage / pageSize) * pageSize + 1 - (curPage % pageSize == 0 ? pageSize : 0);
		int ePage = sPage + pageSize - 1;
		
		if (ePage > pageCount)
		{
			ePage = pageCount;
		}
		
		model.addAttribute("adminList", adminList);
		model.addAttribute("sPage", sPage);
		model.addAttribute("ePage", ePage);
		model.addAttribute("curPage", curPage);

		return "manage/auth/authManage";
	}
	
	@RequestMapping("/popup/regAuthSchView")
	public String regAuthSchView(Model model)
	{
		return "popup/regAuthSchView";
	}
	
	@RequestMapping("/manage/auth/regAuthSchAction")
	public String regAuthSchAction(Model model, @ModelAttribute("auth") AdminAuth auth)
	{
		model.addAttribute("userList", manageService.getAdminAddSch(auth));
		
		return "ajax/ajaxRegAuthSch";
	}
	
	@RequestMapping("/manage/auth/authRegAction")
	public String regAuthAction(Model model, @ModelAttribute("auth") AdminAuth auth)
	{
		model.addAttribute("result", manageService.regAdmin(auth));
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/auth/authUptAction")
	public String uptAuthAction(Model model, @ModelAttribute("auth") AdminAuth auth)
	{
		model.addAttribute("result", manageService.uptAdmin(auth));
		
		return "ajax/ajaxResult";
	}
	
	@RequestMapping("/manage/auth/authDelAction")
	public String delAuthAction(Model model, @ModelAttribute("auth") AdminAuth auth)
	{
		model.addAttribute("result", manageService.delAdmin(auth));
		
		return "ajax/ajaxResult";
	}
}
