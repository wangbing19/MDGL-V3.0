package com.vision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.Appointment;

import com.vision.vo.SysResult;
import com.vision.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/user")
	@ResponseBody
	public SysResult fallAll() {
		List<Appointment> result=userService.fallAll();
		return SysResult.oK(result);
	}
	
}
