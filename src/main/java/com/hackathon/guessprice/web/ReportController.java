package com.hackathon.guessprice.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.PieReportDto;

@Controller
public class ReportController {
	@RequestMapping(value="/getPieReport",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "pie";
	}
	
	@RequestMapping(value="/getPieData",method=RequestMethod.GET)
	@ResponseBody
	public List<PieReportDto> getReportPieData(){
		PieReportDto dto = new PieReportDto();
		dto.setName("FireFox");
		dto.setShare(19.02f);
		
		PieReportDto dto2 = new PieReportDto();
		dto2.setName("IE");
		dto2.setShare(80.98f);
		
		List<PieReportDto> result = new ArrayList<PieReportDto>();
		result.add(dto);
		result.add(dto2);
		
		return result;
	}
}

