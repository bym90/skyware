package com.scf.skyware.notice.dao;

import java.util.List;

import com.scf.skyware.mobile.domain.NoticeM;
import com.scf.skyware.notice.domain.Notice;

public interface NoticeDAO
{
	// 리스트 불러오기
	public List<Notice> getNoticeList();
	
	// 페이지 상세 보기
	public Notice getNoticeView(int noticeNo);
	
	// 공지사항 등록
	public void noticeWriteProc(Notice notice);

	// 페이지 정보 구하기
	public int getPageInfo();
	
	// 공지사항 수정
	public void noticeUpdate(Notice notice);
	
	// 공지사항 삭제
	public void noticeDel(int noticeNo);
	
	
	
	public List<NoticeM> getNoticeListM(NoticeM notice);
	public NoticeM getNoticeViewM(NoticeM notice);
	public int getMaxNotice();
	public int getNoticeCount();
	public int noticeWriteProcM(NoticeM notice);
	public int noticeUpdateM(NoticeM notice);
	public int noticeDelM(NoticeM notice);
}
