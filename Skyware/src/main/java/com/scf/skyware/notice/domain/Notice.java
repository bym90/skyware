package com.scf.skyware.notice.domain;

import java.sql.Date;

public class Notice {

//	public int getNowPage() {
//		return nowPage;
//	}
//
//	public void setNowPage(int nowPage) {
//		this.nowPage = nowPage;
//	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeBody() {
		return noticeBody;
	}

	public void setNoticeBody(String noticeBody) {
		this.noticeBody = noticeBody;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeIsShow() {
		return noticeIsShow;
	}

	public void setNoticeIsShow(String noticeIsShow) {
		this.noticeIsShow = noticeIsShow;
	}

	public String getNoticeWriterId()
	{
		return noticeWriterId;
	}

	public void setNoticeWriterId(String noticeWriterId)
	{
		this.noticeWriterId = noticeWriterId;
	}

	private int noticeNo;
	private int rownum;
	private String noticeTitle;
	private String noticeBody;
	private String noticeWriter;
	private Date noticeDate;
	private String noticeIsShow;
	private String noticeWriterId;
//	private int nowPage;
}
