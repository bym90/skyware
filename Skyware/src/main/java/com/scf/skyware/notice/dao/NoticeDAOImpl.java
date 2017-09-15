package com.scf.skyware.notice.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.mobile.domain.NoticeM;
import com.scf.skyware.notice.domain.Notice;

@Repository
public class NoticeDAOImpl implements NoticeDAO
{
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 리스트 불러오기
	@Override
	public List<Notice> getNoticeList()
	{
		return sqlSession.selectList("getNoticeList");
	}
	
	// 페이지 상세 보기
	@Override
	public Notice getNoticeView(int noticeNo){
		return (Notice) sqlSession.selectOne("getNoticeView", noticeNo);
	}
	
	// 페이지 정보 구하기
	@Override
	public int getPageInfo() {
		return (int) sqlSession.selectOne("getPageInfo");
		
	}
	
	// 공지사항 등록
	@Override
	public void noticeWriteProc(Notice notice){
		 sqlSession.insert("noticeWriteProc", notice);
	}
	
	// 공지사항 수정
	@Override
	public void noticeUpdate(Notice notice){
		sqlSession.update("noticeUpdate", notice);
	}
	
	@Override
	public void noticeDel(int noticeNo) {
		sqlSession.update("noticeDel", noticeNo);
	}

	@Override
	public List<NoticeM> getNoticeListM(NoticeM notice)
	{
		return sqlSession.selectList("Notice_getNoticeList_M", notice);
	}

	@Override
	public NoticeM getNoticeViewM(NoticeM notice)
	{
		return sqlSession.selectOne("Notice_getNoticeView_M", notice);
	}

	@Override
	public int noticeWriteProcM(NoticeM notice)
	{
		return sqlSession.insert("Notice_noticeWriteProc_M", notice);
	}

	@Override
	public int noticeUpdateM(NoticeM notice)
	{
		return sqlSession.update("Notice_noticeUpdate_M", notice);
	}

	@Override
	public int noticeDelM(NoticeM notice)
	{
		return sqlSession.update("Notice_noticeDel_M", notice);
	}

	@Override
	public int getMaxNotice()
	{
		return sqlSession.selectOne("");
	}

	@Override
	public int getNoticeCount()
	{
		return sqlSession.selectOne("Notice_getNoticeCount_M");
	}
	
	
}
