package com.scf.skyware.calendar.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scf.skyware.calendar.domain.Calendar_temp;
import com.scf.skyware.calendar.service.CalendarService;

@Controller
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	// 캘린더 리스트
	@RequestMapping("/calendar/calendarList")
	public String calendarList(Model model, HttpServletRequest request) {
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//		String date = fm.format(new Date());

		ArrayList<Calendar_temp> calendarList = calendarService.calendarList();

		model.addAttribute("calendarList", calendarList);

		return "calendar/calendarList";
	}
	
	// 캘린더 Ajax 콜백
	@RequestMapping("/calendar/calendarAjax")
	public String calendarAjax(Model model, HttpServletRequest request) {
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//		String date = fm.format(new Date());

		ArrayList<Calendar_temp> calendarList = calendarService.calendarList();

		model.addAttribute("calendarList", calendarList);

		return "calendar/calendarAjax";
	}
}
