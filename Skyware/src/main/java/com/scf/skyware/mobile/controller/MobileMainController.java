package com.scf.skyware.mobile.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.scf.skyware.common.domain.SessionVO;
import com.scf.skyware.common.util.AdminLoginCheck;
import com.scf.skyware.common.util.Base64;
import com.scf.skyware.common.util.SessionManager;
import com.scf.skyware.main.domain.User;
import com.scf.skyware.main.service.MainService;
import com.scf.skyware.manage.service.ManageService;

import net.sf.json.JSONObject;

@Controller()
public class MobileMainController
{
	private MainService mainService;
	private ManageService manageService;

	@Autowired
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@Autowired
	public void setManageService(ManageService manageService)
	{
		this.manageService = manageService;
	}

	@RequestMapping(value = { "/mobile", "/mobile/index", "/mobile/home" })
	public String index(Model model, HttpServletRequest request, HttpSession session) throws Exception
	{
		return null;
	}

	// @RequestMapping(value={"/mobile/LoginAction"}, method =
	// RequestMethod.POST)
	@RequestMapping("/mobile/LoginAction")
	public String loginAction(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId").toString();
		String userPw = request.getParameter("userPw") == null ? "" : request.getParameter("userPw").toString();

		User u = new User();
		u.setUserId(userId);
		
		User user = mainService.getUser(u);
		
		int result = 0;
		
		if (user == null)
		{
			System.err.println(1);
			result = AdminLoginCheck.ID_NOT_FOUND;
		}
		else if (!Base64.base64Encode(userPw).equals(user.getUserPw()))
		{
			System.err.println(2);
			result = AdminLoginCheck.PASSWORD_MISMATCH;
		}
		else if ("N".equals(user.getUseYn()))
		{
			System.err.println(3);
			result = AdminLoginCheck.ACCESS_DENIED;
		}
		else
		{
			UserSession(user, request);
//			session.setAttribute("userNm", user.getUserId());
//			session.setAttribute("userId", user.getUserNm());
//			session.setAttribute("isLogin", false);
			result = AdminLoginCheck.LOGIN_SUCCESS;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		

		System.err.println(user);
		
		JSONObject json = new JSONObject();

		json.put("result", result);
		
		if (result == AdminLoginCheck.LOGIN_SUCCESS)
		{
			json.put("userInfo", (SessionVO) SessionManager.getSession().getAttribute("sessionVO"));
		}
		else
		{
			json.put("userInfo", null);
		}
		
		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}
	
	//*
	private void UserSession(User user, HttpServletRequest request)
	{
		SessionVO sessionVO = new SessionVO();
		sessionVO.setUserId(user.getUserId());
		sessionVO.setUserPw(user.getUserPw());
		sessionVO.setUserNm(user.getUserNm());
		sessionVO.setUserEmail(user.getUserEmail());
		sessionVO.setUserPhone(user.getUserPhone());
		sessionVO.setUserGender(user.getUserGender());
		sessionVO.setUserAddrFull(user.getUserAddrFull());
		sessionVO.setUserAddrFullRoad(user.getUserAddrFullRoad());
		sessionVO.setUserAddrSido(user.getUserAddrSido());
		sessionVO.setUserAddrSigungu(user.getUserAddrSigungu());
		sessionVO.setUserAddrEMD(user.getUserAddrEMD());
		sessionVO.setUserAddrJibun(user.getUserAddrJibun());
		sessionVO.setUserAddrRoad(user.getUserAddrRoad());
		sessionVO.setUserAddrBuldNo(user.getUserAddrBuldNo());
		sessionVO.setUserAddrDetail(user.getUserAddrDetail());
		sessionVO.setUserZipcode(user.getUserZipcode());
		sessionVO.setUserBirth(user.getUserBirth());
		sessionVO.setUserSL(user.getUserSL());
		sessionVO.setUserRegDate(user.getUserRegDate());
		sessionVO.setUserModDate(user.getUserModDate());
		sessionVO.setUserJoinDate(user.getUserJoinDate());
		sessionVO.setUseYn(user.getUseYn());
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionVO", sessionVO);
		SessionManager.setSession(session);
	}//*/

	@RequestMapping("/mobile/LogoutAction")
	public String logout(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
//		session.setAttribute("userNm", "nonmem");
//		session.setAttribute("userId", "nonmem");
//		session.setAttribute("isLogin", false);
		session.setAttribute("sessionVO", null);

		JSONObject json = new JSONObject();

//		json.put("result", result);
//		json.put("userSession", null);

		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}

	@RequestMapping("/mobile/getAddress")
	public String getAddress(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String keyword = URLDecoder.decode(request.getParameter("keyword") == null ? "" : request.getParameter("keyword"), "UTF-8"); // 검색
		String curPage = request.getParameter("curPage") == null ? "0" : request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize") == null ? "100" : request.getParameter("pageSize");
		int totalCount = 0; // 총 주소 건수

		List<HashMap<String, String>> addrList = new ArrayList<HashMap<String, String>>();

		// 검색어가 있을 경우
		if (!keyword.equals(""))
		{
			String confmKey = "U01TX0FVVEgyMDE3MDcxNDE3MTc1NTIyOTE2"; // 인증키

			// 현재 페이지 지정하지 않을 경우 1페이지로 설정
			if (curPage.equals("") || curPage.equals("0"))
				curPage = "1";

			// 페이지별 개수를 지정하지 않을 경우 100개로 설정
			if (pageSize.equals("") || pageSize.equals("0"))
				pageSize = "100";

			String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=" + curPage + "&countPerPage=" + pageSize + "&keyword="
					+ URLEncoder.encode(keyword, "UTF-8") + "&confmKey=" + confmKey;
			URL url = new URL(apiUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String tempStr = null;

			InputSource is = null;
			
			while (true)
			{
				tempStr = br.readLine();
				if (tempStr == null)
				{
					break;
				}
				is = new InputSource(new StringReader(tempStr));
			}

			// XML Document 객체 생성
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			// xpath 생성
			XPath xpath = XPathFactory.newInstance().newXPath();

			NodeList userAddrFullNode = (NodeList) xpath.evaluate("//results//juso//jibunAddr", document, XPathConstants.NODESET);
			NodeList userAddrFullRoadNode = (NodeList) xpath.evaluate("//results//juso//roadAddr", document, XPathConstants.NODESET);
			NodeList userAddrSidoNode = (NodeList) xpath.evaluate("//results//juso//siNm", document, XPathConstants.NODESET);
			NodeList userAddrSigunguNode = (NodeList) xpath.evaluate("//results//juso//sggNm", document, XPathConstants.NODESET);
			NodeList userAddrEMDNode = (NodeList) xpath.evaluate("//results//juso//emdNm", document, XPathConstants.NODESET);
			NodeList userAddrJibun1Node = (NodeList) xpath.evaluate("//results//juso//lnbrMnnm", document, XPathConstants.NODESET);
			NodeList userAddrJibun2Node = (NodeList) xpath.evaluate("//results//juso//lnbrSlno", document, XPathConstants.NODESET);
			NodeList userAddrRoadNode = (NodeList) xpath.evaluate("//results//juso//rn", document, XPathConstants.NODESET);
			NodeList userAddrBuld1Node = (NodeList) xpath.evaluate("//results//juso//buldMnnm", document, XPathConstants.NODESET);
			NodeList userAddrBuld2Node = (NodeList) xpath.evaluate("//results//juso//buldSlno", document, XPathConstants.NODESET);
			NodeList userZipcodeNode = (NodeList) xpath.evaluate("//results//juso//zipNo", document, XPathConstants.NODESET);
			NodeList totalCountNode = (NodeList) xpath.evaluate("//common//totalCount", document, XPathConstants.NODESET);

			// 총 주소 건수
			totalCount = Integer.parseInt(totalCountNode.item(0).getTextContent());

			for (int idx = 0; idx < userAddrFullNode.getLength(); idx++)
			{
				HashMap<String, String> map = new HashMap<String, String>();

				String userAddrFull = userAddrFullNode.item(idx).getTextContent();
				String userAddrFullRoad = userAddrFullRoadNode.item(idx).getTextContent();
				String userAddrSido = userAddrSidoNode.item(idx).getTextContent();
				String userAddrSigungu = userAddrSigunguNode.item(idx).getTextContent();
				String userAddrEMD = userAddrEMDNode.item(idx).getTextContent();
				String userAddrJibun = userAddrJibun1Node.item(idx).getTextContent() + "-" + userAddrJibun2Node.item(idx).getTextContent();
				String userAddrRoad = userAddrRoadNode.item(idx).getTextContent();
				String userAddrBuld = userAddrBuld1Node.item(idx).getTextContent() + "-" + userAddrBuld2Node.item(idx).getTextContent();
				String userZipcode = userZipcodeNode.item(idx).getTextContent();

				map.put("userAddrFull", userAddrFull);
				map.put("userAddrFullRoad", userAddrFullRoad);
				map.put("userAddrSido", userAddrSido);
				map.put("userAddrSigungu", userAddrSigungu);
				map.put("userAddrEMD", userAddrEMD);
				map.put("userAddrJibun", userAddrJibun);
				map.put("userAddrRoad", userAddrRoad);
				map.put("userAddrBuld", userAddrBuld);
				map.put("userZipcode", userZipcode);

				addrList.add(map);
			}

			br.close();

		}
		else
		{
			
		}

		// 한글깨짐 방지
		response.setContentType("text/html;charset=UTF-8");

		// 리턴 JSONObject 객체 선언
		JSONObject json = new JSONObject();

		json.put("totalCount", totalCount);
		json.put("addrList", addrList);

		response.getWriter().write(JSONObject.fromObject(json).toString());

		return null;
	}

//	@RequestMapping("/mobile/popup/regUserView")
//	public String joinUserView(Model model, HttpServletRequest request)
//	{
//		return null;
//	}

	@RequestMapping("/mobile/regUserAction")
	public String joinUserAction(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User u) throws Exception
	{
//		String userId = request.getParameter("userId");
//	    String userPw = request.getParameter("userPw");
//	    String userNm = request.getParameter("userNm");
//	    String userEmail = request.getParameter("userEmail");
//	    String userPhone = request.getParameter("userPhone");
//	    String userGender = request.getParameter("userGender");
//	    String userSL = request.getParameter("userSL");
//	    String userBirth = request.getParameter("userBirth");
//	    String userAddrFull = request.getParameter("userAddrFull");
//	    String userAddrDetail = request.getParameter("userAddrDetail");
//	    String userAddrFullRoad = request.getParameter("userAddrFullRoad");
//	    String userAddrSido = request.getParameter("userAddrSido");
//	    String userAddrSigungu = request.getParameter("userAddrSigungu");
//	    String userAddrEMD = request.getParameter("userAddrEMD");
//	    String userAddrJibun = request.getParameter("userAddrJibun");
//	    String userAddrRoad = request.getParameter("userAddrRoad");
//	    String userAddrBuldNo = request.getParameter("userAddrBuldNo");
//	    String userZipcode = request.getParameter("userZipCode");
//	    
//	    User u = new User();
//	    
//	    u.setUserId(userId);
//	    u.setUserPw(Base64.base64Encode(userPw));
//	    u.setUserNm(userNm);
//	    u.setUserEmail(userEmail);
//	    u.setUserPhone(userPhone);
//	    u.setUserGender(userGender);
//	    u.setUserSL(userSL);
//	    u.setUserBirth(userBirth);
//	    u.setUserAddrFull(userAddrFull);
//	    u.setUserAddrFullRoad(userAddrFullRoad);
//	    u.setUserAddrDetail(userAddrDetail);
//	    u.setUserAddrSido(userAddrSido);
//	    u.setUserAddrSigungu(userAddrSigungu);
//	    u.setUserAddrEMD(userAddrEMD);
//	    u.setUserAddrJibun(userAddrJibun);
//	    u.setUserAddrRoad(userAddrRoad);
//	    u.setUserAddrBuldNo(userAddrBuldNo);
//	    u.setUserZipcode(userZipcode);
		
		int result = mainService.regUser(u);

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

	@RequestMapping("/mobile/overlapIdCheck")
	public String overlapIdCheck(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		User u = new User();
		String userId = request.getParameter("userId");
		u.setUserId(userId);
		User user = mainService.getUser(u);
		boolean result = false;

		if (user != null)
		{
			if (userId.equals(user.getUserId()))
			{
				result = true;
			}
		}

		//model.addAttribute("result", result);
		
		JSONObject json = new JSONObject();

		json.put("result", result);

		response.getWriter().write(JSONObject.fromObject(json).toString());


		return null;
	}
}
