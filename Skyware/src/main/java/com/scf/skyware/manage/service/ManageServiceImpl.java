package com.scf.skyware.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.common.util.PageUtil;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.manage.dao.ManageDAO;
import com.scf.skyware.manage.domain.AdminAuth;
import com.scf.skyware.manage.domain.Code;

@Service
public class ManageServiceImpl implements ManageService
{
	private ManageDAO manageDAO;
	
	@Autowired
	public void setManageDAO(ManageDAO manageDAO)
	{
		this.manageDAO = manageDAO;
	}
	
	@Override
	public List<Code> getCodeList(Code c)
	{
		return manageDAO.getCodeList(c);
	}

	@Override
	public int regNamingCode(Code c)
	{
		return manageDAO.regNamingCode(c);
	}

	@Override
	public String getMaxNamingCodeSeq(String CodeDiv)
	{
		return manageDAO.getMaxNamingCodeSeq(CodeDiv);
	}

	@Override
	public int getMaxNamingCodeSort(String CodeDiv)
	{
		return manageDAO.getMaxNamingCodeSort(CodeDiv);
	}

	@Override
	public List<User> getUserList(User u)
	{
		return manageDAO.getUserList(u);
	}
	
	@Override
	public int getUserListCount(User u)
	{
		return manageDAO.getUserListCount(u);
	}

	@Override
	public PageUtil getPageInfo(int cPage)
	{
		int totalCount = manageDAO.getPageInfo();

		return new PageUtil(cPage, totalCount, 5, 5);
	}

	@Override
	public int regBaseCode(Code c)
	{
		return manageDAO.regBaseCode(c);
	}

	@Override
	public int codeDuplCheck(Code c)
	{
		return manageDAO.codeDuplCheck(c);
	}
	
	@Override
	public int uptNamingCode(Code c)
	{
		return manageDAO.uptNamingCode(c);
	}

	@Override
	public int delNamingCode(Code c)
	{
		return manageDAO.delNamingCode(c);
	}

	@Override
	public List<AdminAuth> getAdminList(AdminAuth admin)
	{
		return manageDAO.getAdminList(admin);
	}

	@Override
	public int getAdminListCount(AdminAuth admin)
	{
		return manageDAO.getAdminListCount(admin);
	}

	@Override
	public int uptAdmin(AdminAuth admin)
	{
		return manageDAO.uptAdmin(admin);
	}

	@Override
	public int delAdmin(AdminAuth admin)
	{
		return manageDAO.delAdmin(admin);
	}

	@Override
	public List<AdminAuth> getAdminAddSch(AdminAuth admin)
	{
		return manageDAO.getAdminAddSch(admin);
	}

	@Override
	public int regAdmin(AdminAuth admin)
	{
		return manageDAO.regAdmin(admin);
	}

	@Override
	public AdminAuth getAdminChk(AdminAuth admin)
	{
		return manageDAO.getAdminChk(admin);
	}
}
