package com.vision.service.ppo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vision.exception.ServiceException;
import com.vision.mapper.ppo.PpoAppointmentTimeMapper;
import com.vision.pojo.ppo.PpoAppointmentTime;
import com.vision.service.ppo.PpoAppointmentTimeService;
@Service
public class PpoAppointmentTimeServiceImpl implements PpoAppointmentTimeService{
	@Autowired
	PpoAppointmentTimeMapper ppoAppointmentTimeMapper;
	/**
	 * 添加训练师时间
	 */
	@Override
	public Integer doInsertAppointment(PpoAppointmentTime ppoAppointmentTime) {
		if(ppoAppointmentTime ==null)
			throw new ServiceException("保存数据不能为空");
		if(ppoAppointmentTime.getAppointmentId() == null)
			throw new ServiceException("训练师id不能为空");
		if(ppoAppointmentTime.getStartTime() == null || ppoAppointmentTime.getEndTime() == null) {
			throw new ServiceException("训练师训练开始时间或结束时间不能为空");
		}
		ppoAppointmentTime.setGmtModified(new Date());
		ppoAppointmentTime.setGmtCreate(new Date());
		int insert = ppoAppointmentTimeMapper.insert(ppoAppointmentTime);
		return insert;
	}
	
	/**
	 * 查询训练时间
	 */
	@Override
	public List<PpoAppointmentTime> dofindappointmentTime(Integer ppoAppointmentId) {
		if(ppoAppointmentId == null) {
			throw new ServiceException("训练师id不能为空");
		}
		QueryWrapper<PpoAppointmentTime> queryWrapper = new QueryWrapper<PpoAppointmentTime>();
		queryWrapper.eq("appointment_id", ppoAppointmentId);
		List<PpoAppointmentTime> selectList = ppoAppointmentTimeMapper.selectList(queryWrapper);
		return selectList;
	}
	
	
	
}
