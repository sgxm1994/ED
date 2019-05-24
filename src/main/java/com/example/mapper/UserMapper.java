package com.example.mapper;

import java.util.Map;

import com.example.model.User;

public interface UserMapper {
	String selectByPrimaryKey(Integer id);
	Integer alertUserPhone(Map<String,Object> map);
	Integer alertUserwx(Map<String,Object> map);
	User selectUserInfo(Map<String,Object> map);
	Integer delAlertUserWx(Map<String,Object> map);
}
