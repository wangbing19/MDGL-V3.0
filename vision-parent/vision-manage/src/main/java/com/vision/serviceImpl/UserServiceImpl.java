package com.vision.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vision.mapper.UserMapper;
import com.vision.pojo.Appointment;
import com.vision.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	@Override
	public List<Appointment> fallAll() {
		
		
		 List<Appointment> selectList = userMapper.selectList(null);
		
		return selectList;
	}

	

}
