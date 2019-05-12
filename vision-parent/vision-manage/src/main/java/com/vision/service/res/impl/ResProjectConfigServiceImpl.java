package com.vision.service.res.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vision.exception.ServiceException;
import com.vision.mapper.res.ResProjectConfigMapper;
import com.vision.pojo.res.ResProjectConfig;
import com.vision.service.res.ResProjectConfigService;
import com.vision.vo.PageObject;

@Service
public class ResProjectConfigServiceImpl implements ResProjectConfigService{
	@Autowired
	private  ResProjectConfigMapper resProjectConfigMapper;
	@Override
	public PageObject<ResProjectConfig> dofindObjects(Long userId, String projectName, Integer pageCurrent) {
		if(userId ==null)
			throw new ServiceException("门店id不能为空");
		
		//int count = resProjectConfigMapper.getRowCount(null,userId);
		int count = resProjectConfigMapper.getRowCount(projectName, userId);
		int pageSize=10;
        int startIndex=(pageCurrent-1)*pageSize;
        List<ResProjectConfig> records = resProjectConfigMapper.findResProjectConfigList(startIndex, pageSize);
        PageObject<ResProjectConfig> pageObject = new PageObject<ResProjectConfig>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        return pageObject;
		
	}
	
	/**
	 * 添加项目配置
	 */
	@Override
	public int doSaveObject(ResProjectConfig entity) {
		if(entity.getUserId()==null)
			throw new ServiceException("门店id不能为空");
		int insert = resProjectConfigMapper.insert(entity);
		return insert;
	}

	/**
	 * 修改配置项
	 */
	@Override
	public Integer doUpdate(ResProjectConfig entity) {
		if(entity.getUserId()==null)
			throw new ServiceException("门店id不能为空");
		UpdateWrapper<ResProjectConfig> updateWrapper = new UpdateWrapper();
		updateWrapper.eq("user_id", entity.getUserId()).eq("id", entity.getId());
		int update = resProjectConfigMapper.update(entity, updateWrapper);
		return update;
	}

	/**
	 * 删除配置项
	 */
	@Override
	public int doDelete(Integer[] ids,Integer userId) {
		List<Integer> list = new ArrayList<>();
		int delete = 0;
		for (Integer id : ids) {
			QueryWrapper<ResProjectConfig> queryWrapper = new QueryWrapper();
			queryWrapper.eq("id", id).eq("user_id",userId);
			delete += resProjectConfigMapper.delete(queryWrapper);
		}
		
		
		return delete;
		
	}

	@Override
	public Integer doprojectStateById(Integer id, Integer projectState, Integer userId) {
		ResProjectConfig entity = new ResProjectConfig();
		entity.setProjectState(projectState);
		entity.setGmtModified(new Date());
		UpdateWrapper<ResProjectConfig> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",id).eq("user_id", userId).eq("project_state", projectState);


		Integer i = resProjectConfigMapper.update(entity, updateWrapper);
		return i;
	}
	
	
}
