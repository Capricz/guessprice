/**
 * 
 */
package com.hackathon.guessprice.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.UserDto;


@Controller
@RequestMapping("/user")
public class DemoController {
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserList() {
		List<UserDto> list = new ArrayList<>();
		UserDto user = new UserDto();
		user.setUserId(1);
		user.setUsername("user1");
		list.add(user);
		
		user = new UserDto();
		user.setUserId(2);
		user.setUsername("user2");
		list.add(user);
		
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", "1");
		modelMap.put("data", list);
		modelMap.put("success", "true");
		return modelMap;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public UserDto getUserById(@PathVariable String id)
	{
		UserDto user = new UserDto();
		user.setUserId(Integer.valueOf(id));
		user.setUsername("user");
		return user;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addUser(@RequestBody UserDto user)
	{
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("success", "true");
		return map;
	}
}
