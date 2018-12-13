package com.myssm.core.serviceImpl;

import com.myssm.config.annotation.MyAutowired;
import com.myssm.config.annotation.MyService;
import com.myssm.core.mapper.UserMapper;
import com.myssm.core.pojo.User;
import com.myssm.core.service.UserService;

@MyService
public class UserServiceImpl implements UserService{
	
	@MyAutowired
	UserMapper userMapper;

	@Override
	public User getUser(){
		User user = userMapper.getUserById("11");
		
		return user;
	}
	
}
