package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.model.UserLoginForm;
import com.hackathon.guessprice.model.UserPercentItem;
import com.hackathon.guessprice.model.UserRegisterForm;
import com.hackathon.guessprice.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("currUser")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public UserDto register(@RequestBody UserRegisterForm userRegisterForm,ModelMap modelMap){
//		String username = userRegisterDto.getUsername();
//		String password = userRegisterDto.getPassword();
//		String region = userRegisterDto.getRegion();
		UserDto currUser = null;
		if(userService.isExistUsername(userRegisterForm)){
//			loginDto.setMsg("username exist!");
//			loginDto.setSuccess(false);
//			return "register";
			currUser = new UserDto();
			currUser.setSuccess(false);
		}else{
//			loginDto.setMsg("register successfully!");
//			loginDto.setSuccess(true);
			currUser = userService.register(userRegisterForm);
			currUser.setSuccess(true);
			modelMap.addAttribute("currUser", currUser);
		}
//		return "index";
		return currUser;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public UserDto login(@RequestBody UserLoginForm userLoginForm,ModelMap modelMap){
		String username = userLoginForm.getUsername();
		String password = userLoginForm.getPassword();
		UserDto currUser = userService.login(username,password);
		if(currUser==null){
			currUser = new UserDto();
			currUser.setSuccess(false);
		} else{
			currUser.setSuccess(true);
			modelMap.addAttribute("currUser", currUser);
		}
//		return "index";
		return currUser;
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
