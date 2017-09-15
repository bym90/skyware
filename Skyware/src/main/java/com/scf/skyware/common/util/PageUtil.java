package com.scf.skyware.common.util;

public class PageUtil {
	private int nowPage;
	private int totalCount;

	private int pageList;
	private int groupCount;

	private int totalPage;
	private int startPage;
	private int endPage;
	
	public PageUtil(int nowPage, int totalCount) {
		this(nowPage, totalCount, 10, 7);
	}

	public PageUtil(int nowPage, int totalCount, int pageList, int groupCount) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageList = pageList;
		this.groupCount = groupCount;

		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}

	private void calcTotalPage() {
		totalPage = totalCount / pageList;
		if (totalCount % pageList != 0) {
			totalPage = totalPage + 1;
		}
	}

	private void calcStartPage() {
		startPage = nowPage - (groupCount / 2);

		if (startPage < 1) {
			startPage = 1;
		}
	}

	private void calcEndPage() {
		endPage = startPage + groupCount - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
