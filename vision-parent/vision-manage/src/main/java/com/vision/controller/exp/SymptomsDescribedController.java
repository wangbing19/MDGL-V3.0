package com.vision.controller.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.exp.SymptomsDescribed;
import com.vision.service.exp.SymptomsDescribedService;
import com.vision.vo.JsonResult;

@Controller
@RequestMapping("/symptomsDescribed")
public class SymptomsDescribedController {
	
	@Autowired
	private SymptomsDescribedService symptomsDescribedService;
	
	/**
	 * 通过id查询症状描述表中的数据
	 * 通过前端获取远程诊断表remoteDiagnoseId并查询出remoteDiagnoseId(症状描述表)对应的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectSym")
	@ResponseBody
	public JsonResult selectSym(Integer id) {
		try {
			SymptomsDescribed selectSym = symptomsDescribedService.selectSym(id);
			return JsonResult.oK(selectSym);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "当前数据已不存在");
	}
	
	/**
	 * 症状描述表添加数据
	 * 从浏览器获取添加信息在症状描述表进行添加
	 * @param entity
	 * @return
	 */
	@RequestMapping("/insertSym")
	@ResponseBody
	public JsonResult insertSym(SymptomsDescribed entity) {
		
		try {
			Integer row = symptomsDescribedService.insertSym(entity);
			if(row != 0 || row == null) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加失败");
	}
	
	/**
	 * 症状描述表修改数据
	 * @param entity
	 * @return
	 */
	@RequestMapping("/updateSym")
	@ResponseBody
	public JsonResult updateSym(SymptomsDescribed entity) {
		try {
			Integer row  = symptomsDescribedService.updateSym(entity);
			if(row != 0 || row == null) {
				return JsonResult.oK("修改成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
	}
}
