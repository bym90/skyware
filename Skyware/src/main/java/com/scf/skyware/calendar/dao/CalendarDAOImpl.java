package com.scf.skyware.calendar.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.calendar.domain.Calendar_temp;

@Repository
public class CalendarDAOImpl implements CalendarDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 캘린더 리스트
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<Calendar_temp> calendarList(){
		
		return (ArrayList) sqlSession.selectList("getCalendarList");
		
	}
}
