package com.hackathon.guessprice.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.UserDto;

@Controller
@RequestMapping("/json")
public class JSONController {

	/*@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUserList2() {
//	    logger.info("列表");
	    List<UserDto> list = new ArrayList<UserDto>();
	    UserDto um = new UserDto();
	    um.setUserId(1);
	    um.setUsername("user1");
	    list.add(um);
	    
	    um = new UserDto();
	    um.setUserId(2);
	    um.setUsername("user2");
	    list.add(um);
	    
	    
	    Map<String, Object> modelMap = new     HashMap<String, Object>(3);
	    modelMap.put("total", "1");
	    modelMap.put("data", list);
	    modelMap.put("success", "true");
	    
	    return modelMap;
	}*/
	
//	@RequestMapping(value = "/json")
	@ResponseBody
	public UserDto getUserList() {
//	    logger.info("列表");
//	    List<UserDto> list = new ArrayList<UserDto>();
	    UserDto um = new UserDto();
	    um.setUserId(1);
	    um.setUsername("user1");
//	    list.add(um);
	    
//	    um = new UserDto();
//	    um.setUserId(2);
//	    um.setUsername("user2");
//	    list.add(um);
	    
	    
//	    Map<String, Object> modelMap = new     HashMap<String, Object>(3);
//	    modelMap.put("total", "1");
//	    modelMap.put("data", list);
//	    modelMap.put("success", "true");
	    
//	    return modelMap;
	    return um;
	}

}
