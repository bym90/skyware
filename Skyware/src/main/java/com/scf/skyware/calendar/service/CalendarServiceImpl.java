package com.scf.skyware.calendar.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.skyware.calendar.dao.CalendarDAO;
import com.scf.skyware.calendar.domain.Calendar_temp;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarDAO calendarDAO;

	// 캘린더 리스트
	@Override
	public ArrayList<Calendar_temp> calendarList() {

		ArrayList<Calendar_temp> calendarList = calendarDAO.calendarList();

		return calendarList;
	}
}
