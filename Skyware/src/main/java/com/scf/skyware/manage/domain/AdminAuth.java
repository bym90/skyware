package com.scf.skyware.manage.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AdminAuth
{
	private String userId;
	private String userNm;
	private String permissionType;
	private String userMntYn;
	private String codeMntYn;
	private String boardMntYn;
	private String calenderMntYn;
	private Timestamp authRegDate;
	private Timestamp authUptDate;
	
	//페이징
	private int pageSize;
	private int totalPage;
	private int curPage;
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getUserNm()
	{
		return userNm;
	}
	
	public void setUserNm(String userNm)
	{
		this.userNm = userNm;
	}
	
	public String getPermissionType()
	{
		return permissionType;
	}
	
	public void setPermissionType(String permissionType)
	{
		this.permissionType = permissionType;
	}
	public String getUserMntYn()
	{
		return userMntYn;
	}
	
	public void setUserMntYn(String userMntYn)
	{
		this.userMntYn = userMntYn;
	}
	
	public String getCodeMntYn()
	{
		return codeMntYn;
	}
	
	public void setCodeMntYn(String codeMntYn)
	{
		this.codeMntYn = codeMntYn;
	}
	
	public String getBoardMntYn()
	{
		return boardMntYn;
	}
	
	public void setBoardMntYn(String boardMntYn)
	{
		this.boardMntYn = boardMntYn;
	}
	
	public String getCalenderMntYn()
	{
		return calenderMntYn;
	}
	
	public void setCalenderMntYn(String calenderMntYn)
	{
		this.calenderMntYn = calenderMntYn;
	}
	
	public Timestamp getAuthRegDate()
	{
		return authRegDate;
	}
	
	public void setAuthRegDate(Timestamp authRegDate)
	{
		this.authRegDate = authRegDate;
	}
	
	public Timestamp getAuthUptDate()
	{
		return authUptDate;
	}

	public void setAuthUptDate(Timestamp authUptDate)
	{
		this.authUptDate = authUptDate;
	}

	public int getPageSize()
	{
		return pageSize;
	}
	
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	public int getTotalPage()
	{
		return totalPage;
	}
	
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}
	
	public int getCurPage()
	{
		return curPage;
	}
	
	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
