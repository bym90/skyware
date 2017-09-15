package com.scf.skyware.mobile.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.scf.skyware.main.domain.User;
import com.scf.skyware.mobile.domain.Schedule;

public interface MobileCalendarService
{
	// 캘린더 정보 불러오기
	public List<Schedule> getScheduleList(HashMap<String, Object> param) throws SQLException;
	
	// 캘린더 일정 등록
	public int scheduleWrite(HashMap<String, Object> param) throws SQLException;
	
	// 캘린더 일정 수정
	public int scheduleModify(HashMap<String, Object> param) throws SQLException;
	
	// 캘린더 일정 삭제
	public void scheduleDelete(Schedule schedule) throws SQLException;
	
	// 캘린더 카테고리 가져오기
	public List<Schedule> scheduleGetCate(Schedule schedule) throws SQLException;
	
	// 캘린더 생일자 가져오기
	public List<User> getBirthList() throws SQLException;
	
	// 캘린더 내가 작성한 일정 가져오기
	public List<Schedule> getWriteMySch(HashMap<String, Object> param) throws SQLException;

	// 캘린더 이번주 일정 가져오기 getWeekList
	public List<Schedule> getWeekList(HashMap<String, Object> param) throws SQLException;
	
}
