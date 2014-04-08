package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/getPieReport2",method = RequestMethod.GET)
	public String getPieReport2(ModelMap model) {
		return "pie2";
	}
	
	@RequestMapping(value="/getPieDataForUser",method=RequestMethod.GET)
	@ResponseBody
	public List<UserPercentItem> getReportPieData(){
		List<UserPercentItem> result = userService.findRegionCountList();
		return result;
	}
	
	@RequestMapping(value="/getColumnReport1",method = RequestMethod.GET)
	public String getColumnReport1(ModelMap model) {
		model.addAttribute("productId", 1);
		return "column";
	}
	
	@RequestMapping(value="/getColumnReport2",method = RequestMethod.GET)
	public String getColumnReport2(ModelMap model) {
		model.addAttribute("productId", 2);
		return "column";
	}
	
	@RequestMapping(value="/getColumnReport3",method = RequestMethod.GET)
	public String getColumnReport3(ModelMap model) {
		model.addAttribute("productId", 3);
		return "column";
	}
	
	@RequestMapping(value="/getColumnReport4",method = RequestMethod.GET)
	public String getColumnReport4(ModelMap model) {
		model.addAttribute("productId", 4);
		return "column";
	}
	
	@RequestMapping(value="/getColumnReport5",method = RequestMethod.GET)
	public String getColumnReport5(ModelMap model) {
		model.addAttribute("productId", 5);
		return "column";
	}
	
	
}

