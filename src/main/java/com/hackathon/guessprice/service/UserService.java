package com.hackathon.guessprice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.guessprice.dao.UserDao;
import com.hackathon.guessprice.entity.User;
import com.hackathon.guessprice.model.UserDto;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public List<UserDto> findUserList(){
		List<UserDto> userDtoList = new ArrayList<>();
		List<User> users = userDao.findAll();
		if(users != null && !users.isEmpty()){
			for (User user : users) {
				UserDto dto = new UserDto();
				dto.setUserId(user.getUserId());
				dto.setUsername(user.getUserName());
				userDtoList.add(dto);
			}
		}
		return userDtoList;
	}
}
