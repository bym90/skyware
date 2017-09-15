package com.scf.skyware.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scf.skyware.mobile.domain.NoticeM;
import com.scf.skyware.notice.service.NoticeService;

import net.sf.json.JSONObject;

@Controller
public class MobileNoticeController
{
	private NoticeService noticeService;

	@Autowired
	public void setNoticeService(NoticeService noticeService)
	{
		this.noticeService = noticeService;
	}

	@RequestMapping("/mobile/board/noticeList")
	public String noticeList(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("noticeM") NoticeM n) throws Exception
	{
		int curPage = request.getParameter("curPage") == null ? 1 : "".equals(request.getParameter("curPage")) ? 1 : Integer.parseInt(request.getParameter("curPage").toString());
		int pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize").toString());
		String schState = request.getParameter("schState") == null ? "" : request.getParameter("schState").toString();
		String schType = request.getParameter("schType") == null ? "" : request.getParameter("schType").toString();
		String schText = request.getParameter("schText") == null ? "" : request.getParameter("schText").toString();
		
		NoticeM notice = new NoticeM();
		notice.setCurPage((curPage - 1) * 10);
		notice.setPageSize(pageSize);
		notice.setSchState(schState);
		notice.setSchType(schType);
		notice.setSchText(schText);

		List<NoticeM> noticeList = noticeService.getNoticeListM(notice);

		int totalPage = noticeService.getNoticeCount();
		
		int pageCount = totalPage / pageSize + (totalPage % pageSize == 0 ? 0 : 1);
		int sPage = (curPage / pageSize) * pageSize + 1 - (curPage % pageSize == 0 ? pageSize : 0);
		int ePage = sPage + pageSize - 1;
		
		if (ePage > pageCount)
		{
			ePage = pageCount;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		
		JSONObject json = new JSONObject();

		json.put("noticeList", noticeList);
		json.put("sPage", sPage);
		json.put("ePage", ePage);
		json.put("curPage", curPage);
		json.put("totalCnt", totalPage);
		
		response.getWriter().write(JSONObject.fromObject(json).toString());
		
		return null;
	}

	/*
	 * @RequestMapping("/mobile/board/noticeAjaxList") public String
	 * noticeAjaxList(Model model, HttpServletRequest req) {
	 * System.out.println("들어오냐?1"); System.out.println("들어오냐?2"); String
	 * strPage = req.getParameter("nowPage"); int nowPage = 1; if
	 * (!StringUtils.isNullOrEmpty(strPage)) { nowPage =
	 * Integer.parseInt(strPage); }
	 * 
	 * PageUtil pInfo = noticeService.getPageInfo(nowPage); ArrayList<Notice>
	 * noticeList = noticeService.getNoticeList(pInfo);
	 * 
	 * model.addAttribute("noticeList", noticeList); model.addAttribute("PINFO",
	 * pInfo);
	 * 
	 * return "board/noticeAjaxList"; }
	 */

	@RequestMapping("/mobile/board/noticeView")
	public String noticeView(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("noticeM") NoticeM n) throws Exception
	{
		NoticeM notice = noticeService.getNoticeViewM(n);

		
		response.setContentType("text/html;charset=UTF-8");
		
		JSONObject json = new JSONObject();
		
		json.put("notice", notice);
		
		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}

	@RequestMapping("/mobile/board/noticeWrite")
	public String noticeWrite(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("noticeM") NoticeM n) throws Exception
	{
		int result = noticeService.noticeWriteProcM(n);
		System.err.println(n.toString());
		
		response.setContentType("text/html;charset=UTF-8");

		JSONObject json = new JSONObject();
		
		if (result > 0)
		{
			json.put("result", "S00000");
		}
		else
		{
			json.put("result", "F00001");
		}
		
		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}
//
//	@RequestMapping("/mobile/board/noticeWriteProc")
//	public String noticeWriteProc(Model model, @ModelAttribute("notice") NoticeM notice, HttpServletRequest request, HttpSession session)
//	{
//		String strPage = request.getParameter("nowPage");
//		int nowPage = 1;
//		if (!StringUtils.isNullOrEmpty(strPage))
//		{
//			nowPage = Integer.parseInt(strPage);
//		}
//
//		// String userId = (String) session.getAttribute("userId");
//		String userNm = (String) session.getAttribute("userNm");
//
//		String temp = notice.getNoticeBody();
//		String noticeBody = temp.replaceAll("\r\n", "<br>");
//
//		notice.setNoticeBody(noticeBody);
//		notice.setNoticeWriter(userNm);
//
//		noticeService.noticeWriteProc(notice);
//
//		model.addAttribute("nowPage", nowPage);
//
//		return null;
//	}
//
//	@RequestMapping("/mobile/board/noticeUpdateForm")
//	public String noticeUpdateForm(Model model, @ModelAttribute("notice") NoticeM notice)
//	{
//		String noticeBody = StringUtil.brTo(notice.getNoticeBody());
//		notice.setNoticeBody(noticeBody);
//		model.addAttribute("notice", notice);
//
//		return null;
//	}

	@RequestMapping("/mobile/board/noticeUpdate")
	public String noticeUpdate(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("notice") NoticeM n) throws Exception
	{
		int result = noticeService.noticeUpdateM(n);

		JSONObject json = new JSONObject();

		if (result > 0)
		{
			json.put("result", "S00000");
		}
		else
		{
			json.put("result", "F00001");
		}
		
		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}

	@RequestMapping("/mobile/board/noticeDel")
	public String noticeDel(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("notice") NoticeM n) throws Exception
	{
		int result = noticeService.noticeDelM(n);

		JSONObject json = new JSONObject();

		if (result > 0)
		{
			json.put("result", "S00000");
		}
		else
		{
			json.put("result", "F00001");
		}
		
		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}
}
