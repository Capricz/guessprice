package com.hackathon.guessprice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
//		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "adminMain";
	}
}
