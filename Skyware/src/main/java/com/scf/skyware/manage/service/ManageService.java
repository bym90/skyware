package com.scf.skyware.manage.service;

import java.util.List;

import com.scf.skyware.common.util.PageUtil;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.manage.domain.AdminAuth;
import com.scf.skyware.manage.domain.Code;

public interface ManageService
{
	public List<Code> getCodeList(Code c);
	public int regBaseCode(Code c);
	public int regNamingCode(Code c);
	public int codeDuplCheck(Code c);
	public String getMaxNamingCodeSeq(String CodeDiv);
	public int getMaxNamingCodeSort(String CodeDiv);
	public int uptNamingCode(Code c);
	public int delNamingCode(Code c);
	public List<User> getUserList(User u);
	public int getUserListCount(User u);
	public PageUtil getPageInfo(int cPage);
	public List<AdminAuth> getAdminList(AdminAuth admin);
	public int getAdminListCount(AdminAuth admin);
	public List<AdminAuth>getAdminAddSch(AdminAuth admin);
	public int regAdmin(AdminAuth admin);
	public int uptAdmin(AdminAuth admin);
	public int delAdmin(AdminAuth admin);
	public AdminAuth getAdminChk(AdminAuth admin);
}
