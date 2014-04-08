package com.hackathon.guessprice。snippet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hackathon.guessprice.entity.User;

@Controller  
@RequestMapping("/bbtForum.do")  
@SessionAttributes("currUser") //①将ModelMap中属性名为currUser的属性  //放到Session属性列表中，以便这个属性可以跨请求访问  
public class BbtForumController {
	@RequestMapping(params = "method=listBoardTopic")
	public String listBoardTopic(@RequestParam("id")int topicId, User user,  ModelMap model) {
		bbtForumService.getBoardTopics(topicId);
		System.out.println("topicId:" + topicId);
		System.out.println("user:" + user);
		model.addAttribute("currUser",user); //②向ModelMap中添加一个属性          
		return "listTopic";     
	}
}