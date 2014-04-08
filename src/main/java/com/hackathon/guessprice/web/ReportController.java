package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.UserPercentItem;
import com.hackathon.guessprice.service.UserService;

@Controller
public class ReportController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getPieReport",method = RequestMethod.GET)
	public String getPieReport(ModelMap model) {
		return "pie";
	}
	
	@RequestMapping(value="/getPieDataForUser",method=RequestMethod.GET)
	@ResponseBody
	public List<UserPercentItem> getReportPieData(){
		List<UserPercentItem> result = userService.findRegionCountList();
		return result;
	}
	
	@RequestMapping(value="/getColumnReport",method = RequestMethod.GET)
	public String getColumnReport(ModelMap model) {
		return "column";
	}
}

