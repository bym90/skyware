package com.scf.skyware.mobile.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.main.domain.User;
import com.scf.skyware.mobile.dao.MobileCalendarDAO;
import com.scf.skyware.mobile.domain.Schedule;

@Service
public class MobileCalendarServiceImpl implements MobileCalendarService
{
	private MobileCalendarDAO mobileCalendarDAO;
	
	@Autowired
	public void setMobileDAO(MobileCalendarDAO mobileCalendarDAO)
	{
		this.mobileCalendarDAO = mobileCalendarDAO;
	}
	
	// 캘린더 정보 불러오기
	@Override
	public List<Schedule> getScheduleList(HashMap<String, Object> param) throws SQLException{
		return mobileCalendarDAO.getScheduleList(param);
	}
	
	// 캘린더 일정 등록
	@Override
	public int scheduleWrite(HashMap<String, Object> param) throws SQLException{
		return mobileCalendarDAO.scheduleWrite(param);
	}
	
	// 캘린더 일정 수정
	@Override
	public int scheduleModify(HashMap<String, Object> param) throws SQLException{
		return mobileCalendarDAO.scheduleModify(param);
	}
	
	// 캘린더 일정 삭제
	@Override
	public void scheduleDelete(Schedule schedule) throws SQLException{
		mobileCalendarDAO.scheduleDelete(schedule);
	}
			
	// 캘린더 카테고리 가져오기
	@Override
	public List<Schedule> scheduleGetCate(Schedule schedule) throws SQLException{
		return mobileCalendarDAO.scheduleGetCate(schedule);
	}
	
	// 캘린더 생일자 가져오기
	@Override
	public List<User> getBirthList() throws SQLException{
		return mobileCalendarDAO.getBirthList();
	}
	
	// 캘린더 내가 작성한 일정가져오기
	@Override
	public List<Schedule> getWriteMySch(HashMap<String, Object> param) throws SQLException{
		return mobileCalendarDAO.getWriteMySch(param);
	}
	
	// 캘린더 이번주 일정 가져오기
	@Override
	public List<Schedule> getWeekList(HashMap<String, Object> param) throws SQLException{
		return mobileCalendarDAO.getWeekList(param);
	}
	
}
