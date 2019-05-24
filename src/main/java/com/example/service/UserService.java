package com.example.service;

import java.util.Map;

import com.example.model.User;

public interface UserService {
	
	public String selectByPrimaryKey(Integer userId);
	public Boolean alertUserPhone(Map<String,Object> map);
	public Boolean alertUserwx(Map<String,Object> map);
	public User getUserInfo(Map<String,Object> map);
	public Boolean delAlertUserWx(Map<String,Object> map);
	
}
