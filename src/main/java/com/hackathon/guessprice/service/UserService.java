package com.hackathon.guessprice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.guessprice.common.Utils;
import com.hackathon.guessprice.dao.UserDao;
import com.hackathon.guessprice.entity.User;
import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.model.UserLoginForm;
import com.hackathon.guessprice.model.UserPercentItem;
import com.hackathon.guessprice.model.UserRegisterForm;

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

	public UserDto login(String username, String password) {
		UserDto dto = null;
		User user = userDao.findUser(username,password);
		if(user != null){
			dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setRegion(user.getRegion());
			dto.setUserType(1);
		}
		return dto;
	}
	
	public boolean isExistUsername(UserRegisterForm userRegisterForm){
		List<User> userList = userDao.findUserByName(userRegisterForm.getUsername());
		if(userList!=null && !userList.isEmpty()){
			return true;
		}
		return false;
	}

	@Transactional
	public UserDto register(UserRegisterForm userRegisterForm) {
		UserDto userDto = new UserDto();
		String username = userRegisterForm.getUsername();
		String password = userRegisterForm.getPassword();
		String region = userRegisterForm.getRegion();
		User u = new User();
		u.setUserName(username);
		u.setPassword(password);
		u.setRegion(region);
		u.setRole(1);
		userDao.save(u);
		userDto.setPassword(password);
		userDto.setRegion(region);
		userDto.setUserId(u.getUserId());
		userDto.setUsername(username);
		userDto.setUserType(1);
		return userDto;
	}
}
