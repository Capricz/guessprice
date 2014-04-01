package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<UserDto> findUserList(){
		return userService.findUserList();
	}
}
