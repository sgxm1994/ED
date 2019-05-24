package com.example.controller;
 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = {"/helloSpringBoot"})
	@ResponseBody
    public String HelloSpring(){
        System.out.println("hello spring boot");
        // String user = userService.selectByPrimaryKey(1);
        //System.out.println(user);
        return "a";
    }
	
	
	/**
	 * @author hyx
	 * @desc 客户修改手机号码
	 * */
	@RequestMapping(path = {"/alertUserPhone.do"},method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> alertUserPhone(String newusername,String oldusername){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("newusername", newusername);
		paramMap.put("oldusername", oldusername);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
			if(newusername==null || "".equals(newusername)) {
				resultMap.put("flag", false);
				resultMap.put("code", "501");
				resultMap.put("Msg", "客户新电话号码不能为空");
				return resultMap;
			}
			if(oldusername==null || "".equals(oldusername)) {
				resultMap.put("flag", false);
				resultMap.put("code", "502");
				resultMap.put("Msg", "客户旧电话号码不能为空");
				return resultMap;
			}
		
		//操作之前判断用户是否存在
		User user = userService.getUserInfo(paramMap);
		if(user==null) {
			resultMap.put("flag", false);
			resultMap.put("code", "503");
			resultMap.put("Msg", "客户电话号码不存在");
			return resultMap;
		}
		
		//执行修改电话号码操作
		boolean  result = userService.alertUserPhone(paramMap);
		if(result) {
			resultMap.put("flag", true);
			resultMap.put("code", "200");
			resultMap.put("Msg", "客户电话号码修改成功");
		}
		else{
			resultMap.put("flag", false);
			resultMap.put("code", "500");
			resultMap.put("Msg", "请确认客户电话号码是否正确");
			
		}
        return resultMap;
    }
	
	/**
	 * @author hyx
	 * @desc 客户绑定微信
	 * */
	
	@RequestMapping(path = {"/alertUserWx.do"},method=RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> alertUserWx(String oldusername,String wxUnionId,String wxOpenId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("oldusername", oldusername);
		paramMap.put("wxUnionId", wxUnionId);
		paramMap.put("wxOpenId", wxOpenId);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(wxUnionId==null || "".equals(wxUnionId)) {
			resultMap.put("flag", false);
			resultMap.put("code", "501");
			resultMap.put("Msg", "wxUnionId不能为空");
			return resultMap;
		}
		if(wxOpenId==null || "".equals(wxOpenId)) {
			resultMap.put("flag", false);
			resultMap.put("code", "502");
			resultMap.put("Msg", "wxOpenId不能为空");
			return resultMap;
		}
		if(oldusername==null || "".equals(oldusername)) {
			resultMap.put("flag", false);
			resultMap.put("code", "503");
			resultMap.put("Msg", "客户不能为空");
			return resultMap;
		}
		
		//操作之前判断用户是否存在
		User user = userService.getUserInfo(paramMap);
		if(user==null) {
			resultMap.put("flag", false);
			resultMap.put("code", "504");
			resultMap.put("Msg", "客户不存在");
			return resultMap;
		}
		
        Boolean result = userService.alertUserwx(paramMap);
        if(result) {
			resultMap.put("flag", true);
			resultMap.put("code", "200");
			resultMap.put("Msg", "客户绑定微信成功");
		}
        else {
        	resultMap.put("flag", false);
			resultMap.put("code", "500");
			resultMap.put("Msg", "客户绑定微信失败");
        	
        }
        return resultMap;
    }
	
	
	/**
	 * @author hyx
	 * @desc 客户取消绑定微信
	 * */
	@RequestMapping(path = {"/delAlertUserWx.do"})
	@ResponseBody
	public Map<String,Object> delAlertUserWx(String oldusername){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("oldusername", oldusername);
		
		//操作之前判断用户是否存在
		User user = userService.getUserInfo(paramMap);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(user==null) {
			resultMap.put("flag", false);
			resultMap.put("code", "504");
			resultMap.put("Msg", "客户不存在");
			return resultMap;
		}
		Boolean result = userService.delAlertUserWx(paramMap);
		if(result) {
			resultMap.put("flag", true);
			resultMap.put("code", "200");
			resultMap.put("Msg", "取消绑定微信成功");
		}
		else {
			resultMap.put("flag", false);
			resultMap.put("code", "500");
			resultMap.put("Msg", "取消绑定微信失败");
			
		}
		
		return resultMap;
	}
	
	/**
	 * @author hyx
	 * @desc 查询用户基本信息
	 * */
	
	@RequestMapping(path = {"/getUserInfo.do"})
	@ResponseBody
	public Boolean getUserInfo(String oldusername) {
		
		boolean result = false;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("oldusername",oldusername);
		User user = userService.getUserInfo(paramMap);
		System.out.println("---------------"+user.toString());
		if(user.getWxOpenId()!=null && !"".equals(user.getWxOpenId())) {
			result=true;
		}
		else {
			result=false;
		}
		System.out.println("result=:"+result);
		return result;
		
	}
	
	
}
