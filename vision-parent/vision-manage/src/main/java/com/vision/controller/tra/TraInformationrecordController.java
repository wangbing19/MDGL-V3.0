package com.vision.controller.tra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.cus.vo.CusVo;
import com.vision.pojo.tra.TraInformationrecord;
import com.vision.service.cus.CusCustomerService;
import com.vision.service.tra.TraInformationrecordService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/traInformationrecord")
public class TraInformationrecordController {
	
	@Autowired
	private TraInformationrecordService traInformationrecordService;
	@Autowired
	private CusCustomerService cusCustomerService;
	
	/**训练记录分页及姓名查询*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects( CusVo cusVo){
		// 获取登录用户的账号
//				Users user=ShiroUtils.getUser(); 
//				cusVo.setUserId(user.getId());
//				cusVo.setUserParentId(user.getParentId());
				cusVo.setUserId(1);
    			cusVo.setUserParentId(0);
		try {
			PageObject<TraInformationrecord> postForObject = traInformationrecordService.findPageObjects(cusVo);
			if(postForObject.getRecords().size()!=0) {
				return JsonResult.oK(postForObject);
			}
		} catch (Exception e) {
			System.out.println("训练记录分页及姓名查询==============错误=======================");
		}
		return  JsonResult.build(201, "查询无数据");
	}
	
	
	/**添加训练记录到数据库*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject( TraInformationrecord entity) {
//		Users user=ShiroUtils.getUser();
//		entity.setUserParentId(user.getParentId());
//		entity.setUserId(user.getId());
		entity.setUserParentId(1);
		entity.setUserId(0);
		try {
			
			Integer rows = traInformationrecordService.doSaveObject(entity);
			if(rows != null && rows != 0) {
				cusCustomerService.updateObjectByTimesOfTraining(entity);
				return JsonResult.oK("保存成功");
			}
		} catch (Exception e) {
			System.out.println("添加训练记录到数据库==============错误=======================");
		}
		return JsonResult.build(201, "添加数据异常,请稍后重试");
	}
	
	
	/**删除*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject( Integer id) {
		try {
			Integer rows = traInformationrecordService.doDeleteObject(id);
			if(rows != null && rows != 0) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("从数据删除训练记录表信息==============错误=======================");
		}
		return JsonResult.build(201, "数据可能已不存在");
	}
	
	
	/**通过id查询*/
	@RequestMapping("doSelectUI")
	@ResponseBody
	public JsonResult doSelect( Integer id) {
		try {
			TraInformationrecord entity = traInformationrecordService.doSelect(id);
			if(entity != null) {
				return JsonResult.oK(entity);
			}
		} catch (Exception e) {
			System.out.println("通过id查询训练表信息==============错误=======================");
		}
		return JsonResult.build(201, "该条数据已不存在");
	}
	
	
	/**通过id修改训练表信息*/
	@RequestMapping("doUpdate")
	@ResponseBody
	public JsonResult doUpdate( TraInformationrecord entity) {
//		Users user = ShiroUtils.getUser();
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		entity.setUserId(1);
		entity.setUserParentId(0);
		try {
			Integer rows = traInformationrecordService.doUpdate(entity);
			if(rows != null && rows != 0) {
				return JsonResult.oK("保存成功");
			}
		} catch (Exception e) {
			System.out.println("通过id修改训练表信息==============错误=======================");
		}
		return JsonResult.build(201, "修改保存数据错误,请稍后重试");
	}
}
