package com.scf.skyware.calendar.dao;

import java.util.ArrayList;

import com.scf.skyware.calendar.domain.Calendar_temp;

public interface CalendarDAO {

	// 캘린더 리스트
	public ArrayList<Calendar_temp> calendarList();
}
