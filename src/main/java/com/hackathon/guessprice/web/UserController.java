package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.model.UserLoginDto;
import com.hackathon.guessprice.model.UserPercentItem;
import com.hackathon.guessprice.model.UserRegisterDto;
import com.hackathon.guessprice.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@ResponseBody
	public UserLoginDto register(@RequestBody UserRegisterDto userRegisterDto){
//		String username = userRegisterDto.getUsername();
//		String password = userRegisterDto.getPassword();
//		String region = userRegisterDto.getRegion();
		return userService.register(userRegisterDto);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public UserLoginDto login(@RequestBody UserLoginDto userLoginDto){
		String username = userLoginDto.getUsername();
		String password = userLoginDto.getPassword();
		return userService.login(username,password);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<UserDto> findUserList(){
		return userService.findUserList();
	}
	
	@RequestMapping("/userPercent")
	@ResponseBody
	public List<UserPercentItem> findUserPercent(){
		return userService.findRegionCountList();
	}
	
	
}
