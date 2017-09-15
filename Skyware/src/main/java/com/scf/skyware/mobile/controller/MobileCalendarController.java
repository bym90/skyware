package com.scf.skyware.mobile.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.mobile.domain.Schedule;
import com.scf.skyware.mobile.service.MobileCalendarService;

import net.sf.json.JSONObject;

@Controller
public class MobileCalendarController {

	private MobileCalendarService mobileCalendarService;

	@Autowired
	public void MobileCalendarService(MobileCalendarService mobileCalendarService) {
		this.mobileCalendarService = mobileCalendarService;
	}

	// 캘린더 정보 가져오기
	@RequestMapping(value = { "/mobile/scheduleList" })
	public String getScheduleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String beforeMonth = request.getParameter("beforeMonth");
		String afterMonth = request.getParameter("afterMonth");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date parseBeforeMonth = format.parse(beforeMonth);
		Date parseAfterMonth = format.parse(afterMonth);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("beforeMonth", parseBeforeMonth);
		param.put("afterMonth", parseAfterMonth);

		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList = mobileCalendarService.getScheduleList(param);

		// 한글깨짐 방지
		response.setContentType("text/html;charset=UTF-8");

		for (Schedule c : scheduleList) {
			System.err.println(c.toString());
		}

		Gson gson = new Gson();
		String json = gson.toJson(scheduleList);

		JSONObject obj = new JSONObject();
		obj.put("calendarList", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}

	// 캘린더 일정 등록
	@RequestMapping(value = { "/mobile/scheduleWrite" })
	public String scheduleWrite(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("schedule") Schedule schedule) throws Exception {
		String schCate1 = request.getParameter("cate1") == null ? "" : request.getParameter("cate1").toString();
		String schCate2 = request.getParameter("cate2") == null ? "" : request.getParameter("cate2").toString();
		String schTitle = request.getParameter("title") == null ? "" : request.getParameter("title").toString();
		String schBody = request.getParameter("content") == null ? "" : request.getParameter("content").toString();
		String schMemo = request.getParameter("memo") == null ? "" : request.getParameter("memo").toString();
		String schCompletion = request.getParameter("completion") == null ? "" : request.getParameter("completion").toString();
		String schWriterId = request.getParameter("writerId") == null ? "" : request.getParameter("writerId").toString();
		String tempStartDate = request.getParameter("startTime") == null ? "" : request.getParameter("startTime");
		String tempEndDate = request.getParameter("endTime") == null ? "" : request.getParameter("endTime");
		String schAllDay = request.getParameter("allDay") == null ? "" : request.getParameter("allDay").toString();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date parseStartDate = format.parse(tempStartDate);
		Date parseEndDate = format.parse(tempEndDate);

		if (schAllDay.equals("true")) {
			schAllDay = "Y";
		} else {
			schAllDay = "N";
		}

		System.err.println("시작날짜" + request.getParameter("startTime").toString());
		System.err.println("종료날짜" + request.getParameter("endTime").toString());
		System.err.println("카테1  " + schCate1);
		System.err.println("카테2  " + schCate2);
		System.err.println("제목  " + schTitle);
		System.err.println("내용  " + schBody);
		System.err.println("메모  " + schMemo);
		System.err.println("진행률  " + schCompletion);
		System.err.println("작성자ID  " + schWriterId);
		System.err.println("시작  " + parseStartDate);
		System.err.println("마감  " + parseEndDate);
		System.err.println("종일  " + schAllDay);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schNo", "");
		param.put("schCate1", schCate1);
		param.put("schCate2", schCate2);
		param.put("schTitle", schTitle);
		param.put("schBody", schBody);
		param.put("schMemo", schMemo);
		param.put("schCompletion", schCompletion);
		param.put("schWriterId", schWriterId);
		param.put("schStartDate", parseStartDate);
		param.put("schEndDate", parseEndDate);
		param.put("schAllDay", schAllDay);

		mobileCalendarService.scheduleWrite(param);

		Gson gson = new Gson();
		String json = gson.toJson(param.get("schNo"));

		JSONObject obj = new JSONObject();
		obj.put("schNo", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}

	@RequestMapping(value = { "/mobile/scheduleModify" })
	public String scheduleModify(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("schedule") Schedule schedule) throws Exception {

		String schNo = request.getParameter("calendarNo") == null ? "" : request.getParameter("calendarNo").toString();
		String schCate1 = request.getParameter("cate1") == null ? "" : request.getParameter("cate1").toString();
		String schCate2 = request.getParameter("cate2") == null ? "" : request.getParameter("cate2").toString();
		String schTitle = request.getParameter("title") == null ? "" : request.getParameter("title").toString();
		String schBody = request.getParameter("content") == null ? "" : request.getParameter("content").toString();
		String schMemo = request.getParameter("memo") == null ? "" : request.getParameter("memo").toString();
		String schCompletion = request.getParameter("completion") == null ? "" : request.getParameter("completion").toString();
		String schWriterId = request.getParameter("writerId") == null ? "" : request.getParameter("writerId").toString();
		String tempStartDate = request.getParameter("startTime") == null ? "" : request.getParameter("startTime");
		String tempEndDate = request.getParameter("endTime") == null ? "" : request.getParameter("endTime");
		String schAllDay = request.getParameter("allDay") == null ? "" : request.getParameter("allDay").toString();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date parseStartDate = format.parse(tempStartDate);
		Date parseEndDate = format.parse(tempEndDate);
		
		System.err.println(parseStartDate);
		System.err.println(parseEndDate);

		if (schAllDay.equals("true")) {
			schAllDay = "Y";
		} else {
			schAllDay = "N";
		}

		System.err.println("글번호 " + schNo);
		System.err.println("카테1  " + schCate1);
		System.err.println("카테2  " + schCate2);
		System.err.println("제목  " + schTitle);
		System.err.println("내용  " + schBody);
		System.err.println("메모  " + schMemo);
		System.err.println("진행률  " + schCompletion);
		System.err.println("작성자ID  " + schWriterId);
		System.err.println("시작  " + parseStartDate);
		System.err.println("마감  " + parseEndDate);
		System.err.println("종일  " + schAllDay);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schNo", schNo);
		param.put("schCate1", schCate1);
		param.put("schCate2", schCate2);
		param.put("schTitle", schTitle);
		param.put("schBody", schBody);
		param.put("schMemo", schMemo);
		param.put("schCompletion", schCompletion);
		param.put("schWriterId", schWriterId);
		param.put("schStartDate", parseStartDate);
		param.put("schEndDate", parseEndDate);
		param.put("schAllDay", schAllDay);

		mobileCalendarService.scheduleModify(param);

		return null;
	}

	// 일정 삭제
	@RequestMapping(value = { "/mobile/scheduleDelete" })
	public String scheduleDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("schedule") Schedule schedule) throws Exception {
		String schNo = request.getParameter("calendarNo");
		schedule.setSchNo(schNo);
		
		mobileCalendarService.scheduleDelete(schedule);
	
		response.setContentType("text/html;charset=UTF-8");
	
		return null;
	}
	
	// 카테고리 가져오기
	@RequestMapping(value = { "/mobile/scheduleGetCate" })
	public String scheduleGetCate(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("schedule") Schedule schedule) throws Exception {

		String schCateDiv = request.getParameter("CATE");
		schedule.setSchCateDiv(schCateDiv);

		List<Schedule> scheduleCateList = new ArrayList<Schedule>();

		scheduleCateList = mobileCalendarService.scheduleGetCate(schedule);

		System.err.println(scheduleCateList);

		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		String json = gson.toJson(scheduleCateList);

		JSONObject obj = new JSONObject();
		obj.put("scheduleCateList", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}
	
	// 이번달 생일자 가져오기
	@RequestMapping(value = { "/mobile/getBirth" })
	public String getBirth(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("user") User user) throws Exception {
		
		List<User> getBirthList = new ArrayList<User>();
		getBirthList = mobileCalendarService.getBirthList();
		response.setContentType("text/html;charset=UTF-8");

		System.err.println(getBirthList);
		
		Gson gson = new Gson();
		String json = gson.toJson(getBirthList);

		JSONObject obj = new JSONObject();
		obj.put("getBirthList", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}

	// 이번주 일정 가져오기
	@RequestMapping(value = { "/mobile/getWeekList" })
	public String getWeekList(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("schedule") Schedule schedule) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar temp = Calendar.getInstance();
		int nowYear = temp.get(Calendar.YEAR);
		int nowMonth = temp.get(Calendar.MONTH) + 1;
		int nowWeek = temp.get(Calendar.WEEK_OF_MONTH);
		int nowday = temp.get(Calendar.DAY_OF_MONTH);
		int nowMonday = temp.get(Calendar.MONDAY);
		System.out.println(temp + "==================겟인스턴스");
		System.out.println(nowYear + "========================이번년");
		System.out.println(nowMonth + "========================이번달");
		System.out.println(nowWeek + "========================몇째주");
		System.out.println(nowday + "========================오늘");
		System.out.println(nowMonday + "========================월요일");

		temp.set(temp.DAY_OF_WEEK, temp.SUNDAY);
		String tempSunday = format.format(temp.getTime());
		Date sunday = format.parse(tempSunday);
		System.out.println(sunday + "이번주 일요일");

//		temp.set(temp.DAY_OF_WEEK, temp.SATURDAY);
//		String tempSaturday = format.format(temp.getTime());
//		Date saturday = format.parse(tempSaturday);
//		System.out.println(saturday + "이번주 토요일");

		temp.add(Calendar.DATE, 7);
		temp.set(temp.DAY_OF_WEEK, temp.SUNDAY);
	 	String tempSaturday = format.format(temp.getTime());		
	 	Date saturday = format.parse(tempSaturday);
	 	System.out.println(saturday + "이번주 토요일 자정까지");

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("nowSunday", sunday);
		param.put("nowSaturday", saturday);

		List<Schedule> getWeekList = new ArrayList<Schedule>();
		getWeekList = mobileCalendarService.getWeekList(param);
		System.err.println(getWeekList);

		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		String json = gson.toJson(getWeekList);

		JSONObject obj = new JSONObject();
		obj.put("getWeekList", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}

	
	// 내가 작성한 일정 가져오기
	@RequestMapping(value = { "/mobile/getWriteMySch" })
	public String getWriteMySch(HttpServletRequest request, HttpServletResponse response, HttpSession session,@ModelAttribute("schedule") Schedule schedule) throws Exception {

		String schWriterId = request.getParameter("writerId") == null ? "" : request.getParameter("writerId").toString();
		System.err.println("작성아이디" + schWriterId);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schWriterId", schWriterId);
//		
//		schedule.setSchWriterId(schWriterId);
		List<Schedule> getWriteMySch = new ArrayList<Schedule>();
		getWriteMySch = mobileCalendarService.getWriteMySch(param);
		
//		for(Schedule s : getWriteMySch){
//			s.getSchStartDate().toString();
//		}
		
		response.setContentType("text/html;charset=UTF-8");
		System.err.println(getWriteMySch);


		Gson gson = new Gson();
		String json = gson.toJson(getWriteMySch);

		JSONObject obj = new JSONObject();
		obj.put("getWriteMySch", json);

		response.getWriter().write(JSONObject.fromObject(obj).toString());

		return null;
	}

}
