package com.vision.service.ppo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vision.exception.ServiceException;
import com.vision.mapper.ppo.PpoAppointmentMapper;
import com.vision.pojo.ppo.PpoAppointment;
import com.vision.service.ppo.PpoAppointmentservice;
import com.vision.vo.PageObject;
@Service
public class PpoAppointmentserviceImpl implements PpoAppointmentservice{
	
	@Autowired
	private PpoAppointmentMapper ppoAppointmentMapper;
/**
 * 查询所有训练师
 */
	@Override
	public PageObject<PpoAppointment> findAll(String appointmentName, Integer pageCurrent) {
		Integer count = ppoAppointmentMapper.selectCount(null);
        int pageSize=3;
        int startIndex=(pageCurrent-1)*pageSize;
        List<PpoAppointment> records = ppoAppointmentMapper.findAppointmentList(startIndex, pageSize);
        PageObject<PpoAppointment> pageObject = new PageObject<PpoAppointment>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        int pageCount=(count-1)/pageSize+1;
   //     System.out.println(pageCount);
        pageObject.setPageCount(pageCount);
        return pageObject;
		
	}
/**
 * 保存训练师
 */
	@Override
	public int saveppoAppointment(PpoAppointment ppoAppointment) {
		if(ppoAppointment ==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getTrainerName()))
			throw new ServiceException("训练师姓名不能为空");
		
		if(ppoAppointment.getShopId() == null)
			throw new ServiceException("门店id不能为空");
		int insert = ppoAppointmentMapper.insert(ppoAppointment);
		return insert;
	}
	/**
	* 修改训练师
	*/
	@Override
	public int updateAppointment(PpoAppointment ppoAppointment) {
		if(ppoAppointment.getId()==null)
			throw new ServiceException("训练师id不能为空");
		ppoAppointment.setGmtModified(new Date());
		UpdateWrapper<PpoAppointment> updateWrapper= new UpdateWrapper<>();
		updateWrapper.eq("shop_id", ppoAppointment.getShopId()).eq("id", ppoAppointment.getId());
		int rows=ppoAppointmentMapper.update(ppoAppointment, updateWrapper);
		if(rows==0)
			throw new ServiceException("该记录可能不存在");
		return rows;
	}
	
	/**
	 * 删除训练师
	 */
	@Override
	public Integer dodeleteAppointment(Integer ppoAppointmentId, Long userId) {
	if(ppoAppointmentId == null)
		throw new ServiceException("训练师id不能为空");
	QueryWrapper<PpoAppointment> queryWrapper = new QueryWrapper<PpoAppointment>();
	queryWrapper.eq("id", ppoAppointmentId).equals(userId);
	int delete = ppoAppointmentMapper.delete(queryWrapper);
	//int deleteById = ppoAppointmentMapper.deleteById(ppoAppointmentId);
	return delete;
}
		
	
}
