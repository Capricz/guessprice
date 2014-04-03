package com.hackathon.guessprice.model;

import java.util.Map;

public class UserDto {

	private int userId;
	private String username;
	private Map<String,Double> regionCountList;
	private String loginName;
	private String loginPasswd;
	private String errMsg;
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPasswd() {
		return loginPasswd;
	}
	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}
	public Map<String, Double> getRegionCountList() {
		return regionCountList;
	}
	public void setRegionCountList(Map<String, Double> regionCountList) {
		this.regionCountList = regionCountList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
