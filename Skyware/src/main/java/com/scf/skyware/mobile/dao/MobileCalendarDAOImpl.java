package com.scf.skyware.mobile.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.main.domain.User;
import com.scf.skyware.mobile.domain.Schedule;


@Repository
public class MobileCalendarDAOImpl implements MobileCalendarDAO
{
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) throws SQLException{
		this.sqlSession = sqlSession;
	}
	
	// 캘린더 정보 불러오기
	@Override
	public List<Schedule> getScheduleList(HashMap<String, Object> param) throws SQLException{
		return sqlSession.selectList("getScheduleList", param);
	}
	
	// 캘린더 일정 등록
	@Override
	public int scheduleWrite(HashMap<String, Object> param) throws SQLException{
		return sqlSession.insert("scheduleWrite", param);
	}
	
	// 캘린더 일정 수정
	@Override
	public int scheduleModify(HashMap<String, Object> param) throws SQLException{
		return sqlSession.insert("scheduleModify", param);
	}
	
	// 캘린더 일정 삭제
	@Override
	public void scheduleDelete(Schedule schedule) throws SQLException{
		sqlSession.update("scheduleDelete", schedule);
	}
	
	// 캘린더 카테고리 가져오기
	@Override
	public List<Schedule> scheduleGetCate(Schedule schedule) throws SQLException{
		return sqlSession.selectList("scheduleGetCate", schedule);
	}
	
	// 캘린더 생일자 가져오기
	@Override
	public List<User> getBirthList() throws SQLException{
		return sqlSession.selectList("getBirthList");
	}
	
	// 캘린더 내가 작성한 일정 가져오기
	@Override
	public List<Schedule> getWriteMySch(HashMap<String, Object> param) throws SQLException{
		return sqlSession.selectList("getWriteMySch", param);
	}
	
	// 캘린더 이번주 일정가져오기
	@Override
	public List<Schedule> getWeekList(HashMap<String, Object> param) throws SQLException{
		return sqlSession.selectList("getWeekList", param);
		
	}

}
