package com.vision.controller.res;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vision.pojo.res.ResProjectConfig;
import com.vision.service.res.ResProjectConfigService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/ResProjectConfig/")
public class ResProjectConfigContorller {

	@Autowired
	private ResProjectConfigService resProjectConfigService;

	

	/**
	 * 查询所有症状类型信息
	 * 
	 * @return
	 */
	@RequestMapping("doFingPageObject")
	@ResponseBody
	public JsonResult dofindObjects(String projectName, Integer pageCurrent) {

		try {
			Long userId = 1L;
			PageObject<ResProjectConfig> result = resProjectConfigService.dofindObjects(userId, projectName,pageCurrent);
			return JsonResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询失败！");
	}

	/**添加*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ResProjectConfig entity) {
		try {
			Integer userId = 1;
			entity.setUserId(userId);
			int result = resProjectConfigService.doSaveObject(entity);
			return JsonResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "添加失败");
	}

	/**修改*/
	@RequestMapping("doUpdate")
	@ResponseBody
	public JsonResult doUpdate( ResProjectConfig entity) {
		try {
			Integer userId = 1;
			entity.setUserId(userId);
			Integer result = resProjectConfigService.doUpdate(entity);
			return JsonResult.oK(result);
			//return JsonResult.oK(11445);
		} catch (Exception e) {
		e.printStackTrace();
	
	}
		return JsonResult.build(201, "修改失败");
		}
	
	/**删除*/
	@RequestMapping("doDelete")
	@ResponseBody
	public JsonResult doDelete( Integer... ids) {
		try {
			Integer userId = 1;
			int result = resProjectConfigService.doDelete(ids,userId);
			 return JsonResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除失败");
	}
	
	/**修改状态*/
	@RequestMapping("doprojectStateById")
	@ResponseBody
	public JsonResult doprojectStateById(Integer id,Integer projectState) {
		try {
			Integer userId = 1;
			Integer doprojectStateById = resProjectConfigService.doprojectStateById(id,projectState,userId);
			return JsonResult.oK(doprojectStateById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改状态失败！");
	}
}
