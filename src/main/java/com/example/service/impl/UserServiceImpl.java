package com.example.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	//测试 
	@Override
	public String selectByPrimaryKey(Integer userId) {
		String res = userMapper.selectByPrimaryKey(userId);
		return res;
	}

	//修改用户电话号码 
	@Override
	public Boolean alertUserPhone(Map<String, Object> map) {
		
		boolean result = false;
		try {
			Integer alertNum = userMapper.alertUserPhone(map);
			if(alertNum>0) result = true;	
			if(alertNum==0) result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

    //绑定微信
	@Override
	public Boolean alertUserwx(Map<String, Object> map) {
		
		boolean result = false;
		try {
			Integer alertNum = userMapper.alertUserwx(map);
			if(alertNum>0) result = true;
			if(alertNum==0) result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//取消绑定微信
	@Override
	public Boolean delAlertUserWx(Map<String, Object> map) {
		boolean result = false;
		try {
			Integer alertNum = userMapper.delAlertUserWx(map);
			if(alertNum>0) result = true;
			if(alertNum==0) result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
    //获取用户信息
	@Override
	public User getUserInfo(Map<String, Object> map) {
		User user = userMapper.selectUserInfo(map);
		return user;
	}

	

}
