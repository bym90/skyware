package com.scf.skyware.notice.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.StringUtils;
import com.scf.skyware.common.util.PageUtil;
import com.scf.skyware.common.util.StringUtil;
import com.scf.skyware.main.service.MainService;
import com.scf.skyware.notice.domain.Notice;
import com.scf.skyware.notice.service.NoticeService;

@Controller
public class NoticeController
{
	
	@Autowired
	private NoticeService noticeService;
	
	//공지사항 리스트
	@RequestMapping("/board/noticeList")
	public String noticeList(Model model, HttpServletRequest request)
	{
		String strPage = request.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PageUtil pInfo = noticeService.getPageInfo(nowPage);
		ArrayList<Notice> noticeList = noticeService.getNoticeList(pInfo);
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("PINFO", pInfo);
		model.addAttribute("nowPage", nowPage);
		
		return "board/noticeList";
	}
	
	//공지사항 Ajax리스트
	/*@RequestMapping("/board/noticeAjaxList")
	public String noticeAjaxList(Model model, HttpServletRequest req)
	{
		System.out.println("들어오냐?1");
		System.out.println("들어오냐?2");
		String strPage = req.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PageUtil pInfo = noticeService.getPageInfo(nowPage);
		ArrayList<Notice> noticeList = noticeService.getNoticeList(pInfo);
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("PINFO", pInfo);
		
		return "board/noticeAjaxList";
	}*/
	
	//공지사항 상세보기
	@RequestMapping("/board/noticeView")
	public String noticeView(Model model, HttpServletRequest request)
	{
		String strPage = request.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String tempNo = request.getParameter("noticeNo");
		int noticeNo = Integer.parseInt(tempNo);
		
		String tempNum = request.getParameter("rownum");
		int rownum = Integer.parseInt(tempNum); 

		Notice notice = noticeService.getNoticeView(noticeNo);
		
		notice.setRownum(rownum);
		
		model.addAttribute("notice", notice);
		model.addAttribute("nowPage", nowPage);
		
		return "board/noticeView";
	}
	
	
	//공지사항 글쓰기폼
	@RequestMapping("board/noticeWrite")
	public String noticeWrite(Model model, HttpServletRequest request)
	{			
		String strPage = request.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}

		model.addAttribute("nowPage", nowPage);
		
		return "board/noticeWrite";
	}
	
	
	//공지사항 등록
	@RequestMapping("board/noticeWriteProc")
	public String noticeWriteProc(Model model, @ModelAttribute("notice") Notice notice, HttpServletRequest request, HttpSession session)
	{
		String strPage = request.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
//		String userId = (String) session.getAttribute("userId");
		String userId = (String) session.getAttribute("userId");
		
		String temp =notice.getNoticeBody();
		String noticeBody = temp.replaceAll("\r\n", "<br>");

		notice.setNoticeBody(noticeBody);
		notice.setNoticeWriterId(userId);
		
		noticeService.noticeWriteProc(notice);
		
		model.addAttribute("nowPage", nowPage);
		
		return "redirect:noticeList";
	}
	
	// 공지사항 수정폼 요청
	@RequestMapping("board/noticeUpdateForm")
	public String noticeUpdateForm(Model model, @ModelAttribute("notice") Notice notice)
	{	
		String noticeBody = StringUtil.brTo(notice.getNoticeBody());
		notice.setNoticeBody(noticeBody);
		model.addAttribute("notice", notice);
		
		return "board/noticeUpdateForm";
	}
	
	// 공지사항 수정
	@RequestMapping("board/noticeUpdate")
	public String noticeUpdate(Model model, @ModelAttribute("notice") Notice notice, HttpServletRequest request)
	{
		String noticeBody = StringUtil.ToBR(notice.getNoticeBody());
		notice.setNoticeBody(noticeBody);

		String strPage = request.getParameter("nowPage");
		int nowPage = 1;
		if (!StringUtils.isNullOrEmpty(strPage)) {
			nowPage = Integer.parseInt(strPage);
		}
		
		String tempNo = request.getParameter("noticeNo");
		int noticeNo = Integer.parseInt(tempNo);
		int rownum = notice.getRownum();
		
		noticeService.noticeUpdate(notice);

		model.addAttribute("noticeNo", noticeNo);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("rownum", rownum);
		
		return "redirect:noticeView";
	}
	
	// 공지사항 삭제
	@RequestMapping("board/noticeDel")
	public String noticeDel(Model model, HttpServletRequest request)
	{

		String temp = request.getParameter("noticeNo");
		int noticeNo = Integer.parseInt(temp);
		
		noticeService.noticeDel(noticeNo);
		
		return "redirect:noticeList";
	}
	
}
