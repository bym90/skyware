package com.scf.skyware.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.common.util.PageUtil;
import com.scf.skyware.mobile.domain.NoticeM;
import com.scf.skyware.notice.dao.NoticeDAO;
import com.scf.skyware.notice.domain.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;


	// 리스트 불러오기
	@Override
	public ArrayList<Notice> getNoticeList(PageUtil pInfo) {
		List<Notice> noticeList;

		noticeList = noticeDAO.getNoticeList();

		int start = (pInfo.getNowPage() - 1) * pInfo.getPageList();
		int end = start + pInfo.getPageList() - 1;

		ArrayList<Notice> resultList = new ArrayList<Notice>();
		for (int i = start; i <= end && i < pInfo.getTotalCount(); i++) {
			resultList.add(noticeList.get(i));
		}
		return resultList;
	}

	//페이지 정보 구하기
	@Override
	public PageUtil getPageInfo(int nowPage) {

		int totalCount = 0;
		totalCount = noticeDAO.getPageInfo();

		PageUtil pInfo = new PageUtil(nowPage, totalCount, 5, 5);
		return pInfo;
	}

	// 페이지 상세 보기
	@Override
	public Notice getNoticeView(int noticeNo) {
		return noticeDAO.getNoticeView(noticeNo);
	}

	// 공지사항 등록
	@Override
	public void noticeWriteProc(Notice notice) {
		noticeDAO.noticeWriteProc(notice);
	}
	
	// 공지사항 수정
	@Override
	public void noticeUpdate(Notice notice) {
		noticeDAO.noticeUpdate(notice);
	}
	
	// 공지사항 삭제
	@Override
	public void noticeDel(int noticeNo) {
		noticeDAO.noticeDel(noticeNo);
	}

	@Override
	public List<NoticeM> getNoticeListM(NoticeM notice)
	{
		return noticeDAO.getNoticeListM(notice);
	}

	@Override
	public NoticeM getNoticeViewM(NoticeM notice)
	{
		return noticeDAO.getNoticeViewM(notice);
	}

	@Override
	public int noticeWriteProcM(NoticeM notice)
	{
		return noticeDAO.noticeWriteProcM(notice);
	}

	@Override
	public int noticeUpdateM(NoticeM notice)
	{
		return noticeDAO.noticeUpdateM(notice);
	}

	@Override
	public int noticeDelM(NoticeM notice)
	{
		return noticeDAO.noticeDelM(notice);
	}

	@Override
	public int getMaxNotice()
	{
		return noticeDAO.getMaxNotice();
	}

	@Override
	public int getNoticeCount()
	{
		return noticeDAO.getNoticeCount();
	}

}
