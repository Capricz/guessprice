package com.hackathon.guessprice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.guessprice.common.Utils;
import com.hackathon.guessprice.dao.UserDao;
import com.hackathon.guessprice.entity.User;
import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.model.UserLoginDto;
import com.hackathon.guessprice.model.UserPercentItem;

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
	
	public List<UserPercentItem> findRegionCountList(){
		List<UserPercentItem> result = new ArrayList<>();
		int countUsers = userDao.countUsers();
		List<Object[]> regionCountList = userDao.findRegionCountList();
		if(countUsers>0 && regionCountList!=null && !regionCountList.isEmpty()){
			for (Object[] o : regionCountList) {
				UserPercentItem item = new UserPercentItem();
				String region = (String) o[0];
				BigInteger regionCount = (BigInteger) o[1];
				Double percent = Utils.calculatePercent(regionCount,countUsers)*100;
				item.setRegion(region);
				item.setPercent(percent);
				result.add(item);
			}
		}
		return result;
	}

	public UserLoginDto login(String username, String password) {
		UserLoginDto dto = new UserLoginDto();
		dto.setUsername(username);
		dto.setPassword(password);
		User user = userDao.findUser(username,password);
		if(user == null){
			dto.setSuccess(false);
			dto.setMsg("username or password is invalid");
		} else{
			dto.setSuccess(true);
			dto.setMsg("login successfully");
		}
		return dto;
		
	}
}
