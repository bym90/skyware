package com.scf.skyware.mobile.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NoticeM
{
	private int noticeNo;
	private int rownum;
	private String noticeTitle;
	private String noticeBody;
	private String noticeWriterId;
	private String noticeDate;
	private String noticeModDate;
	private String noticeIsShow;
	private String noticeWriter;

	// 페이징
	private int pageSize;
	private int totalPage;
	private int curPage;

	// 검색조건
	private String schState;
	private String schType;
	private String schText;

	public int getNoticeNo()
	{
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo)
	{
		this.noticeNo = noticeNo;
	}

	public int getRownum()
	{
		return rownum;
	}

	public void setRownum(int rownum)
	{
		this.rownum = rownum;
	}

	public String getNoticeTitle()
	{
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle)
	{
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeBody()
	{
		return noticeBody;
	}

	public void setNoticeBody(String noticeBody)
	{
		this.noticeBody = noticeBody;
	}

	public String getNoticeWriterId()
	{
		return noticeWriterId;
	}

	public void setNoticeWriterId(String noticeWriterId)
	{
		this.noticeWriterId = noticeWriterId;
	}

	public String getNoticeDate()
	{
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate)
	{
		if (noticeDate != null)
		{
			this.noticeDate = noticeDate.substring(0, 10);
		}
		else
		{
			this.noticeDate = noticeDate;
		}
	}

	public String getNoticeModDate()
	{
		return noticeModDate;
	}

	public void setNoticeModDate(String noticeModDate)
	{
		if (noticeModDate != null)
		{
			this.noticeModDate = noticeModDate.substring(0, 10);
		}
		else
		{
			this.noticeModDate = noticeModDate;
		}
	}

	public String getNoticeIsShow()
	{
		return noticeIsShow;
	}

	public void setNoticeIsShow(String noticeIsShow)
	{
		this.noticeIsShow = noticeIsShow;
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

	public String getSchState()
	{
		return schState;
	}

	public void setSchState(String schState)
	{
		this.schState = schState;
	}

	public String getSchType()
	{
		return schType;
	}

	public void setSchType(String schType)
	{
		this.schType = schType;
	}

	public String getSchText()
	{
		return schText;
	}

	public void setSchText(String schText)
	{
		this.schText = schText;
	}
	
	public String getNoticeWriter()
	{
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter)
	{
		this.noticeWriter = noticeWriter;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
