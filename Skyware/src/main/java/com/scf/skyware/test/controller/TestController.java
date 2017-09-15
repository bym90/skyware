package com.scf.skyware.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scf.skyware.test.domain.Test;
import com.scf.skyware.test.service.TestService;

import net.sf.json.JSONObject;

@Controller
public class TestController
{
	private TestService testService;
	
	@Autowired
	public void setTestService(TestService testService)
	{
		this.testService = testService;
	}
	
	@RequestMapping("/test/testList")
	public String getTestList(Model model, @ModelAttribute("test") Test t)
	{
		List<Test> testList = testService.getTestListAll(t);
		
		model.addAttribute("testList", testList);
		
		return "test/testListView";
	}
	
	@RequestMapping("/test/testListIonic")
	public String getTestListIonic(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("test") Test t) throws IOException
	{
		List<Test> testList = testService.getTestListAll(t);
		
		model.addAttribute("testList", testList);
		
		Map<String, String> test = new HashMap<>();
		List<String> testList1 = new ArrayList<>();
		
		test.put("test1", "test11");
		test.put("test2", "test22");
		
		testList1.add("test1111");
		testList1.add("test2222");
		
		JSONObject json = new JSONObject();
		
		json.put("test", test);
		json.put("testList", testList1);
		
		response.getWriter().write(JSONObject.fromObject(json).toString());
		
		return null;
	}
	
	@RequestMapping("/test/jusoTest")
	public String getTestList(Model model)
	{
		
		return "test/Sample";
	}
}
