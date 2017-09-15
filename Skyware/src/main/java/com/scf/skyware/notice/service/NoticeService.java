package com.scf.skyware.notice.service;

import java.util.ArrayList;
import java.util.List;

import com.scf.skyware.common.util.PageUtil;
import com.scf.skyware.notice.domain.Notice;
import com.scf.skyware.mobile.domain.NoticeM;

public interface NoticeService
{
	// 리스트 불러오기
	public ArrayList<Notice> getNoticeList(PageUtil pInfo);
	
	// 페이지 상세 보기
	public Notice getNoticeView(int noticeNo);
	
	// 페이지 정보 구하기
	public PageUtil getPageInfo(int nowPage);
	
	// 사용자 이름 가져오기
//	public String getUserNm(String userId);
	
	// 공지사항 등록
	public void noticeWriteProc(Notice notice);
	
	// 공지사항 수정
	public void noticeUpdate(Notice notice);
	
	// 공지사항 삭제
	public void noticeDel(int noticeNo);
	
	
	
	public List<NoticeM> getNoticeListM(NoticeM notice);
	public NoticeM getNoticeViewM(NoticeM notice);
	public int noticeWriteProcM(NoticeM notice);
	public int getNoticeCount();
	public int getMaxNotice();
	public int noticeUpdateM(NoticeM notice);
	public int noticeDelM(NoticeM notice);
}
