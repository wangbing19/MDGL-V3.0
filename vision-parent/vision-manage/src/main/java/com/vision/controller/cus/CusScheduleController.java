package com.vision.controller.cus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.cus.CusSchedule;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.service.cus.CusScheduleService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;


@Controller
@RequestMapping("/schedule")
public class CusScheduleController {
	
	@Autowired
	private CusScheduleService cusScheduleService;
	
	
	/**转向课程页面*/
	@RequestMapping("/doScheduleListUI")
	public String doScheduleListUI() {
		return "/pages/sys/cusSchedule_list";
	}
	
	/**转向课程修改页面*/
	@RequestMapping("/doscheduleEditUI")
	public String doscheduleEditUI() {
		return "/pages/sys/cusSchedule_edit";
	}
	
	
	/**基于用户/电话及当前页码值条件查询课程信息*/
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult findPageObjects( CusVo cusVo){
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		//获取登录用户id及上级id
    	cusVo.setUserId(1);
    	cusVo.setUserParentId(0);
		try {
        	PageObject<CusSchedule> pageObject = cusScheduleService.findPageObjects(cusVo);
        	if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			System.out.println("基于用户/电话及当前页码值条件查询课程信息=============错误=================");
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于id删除课程信息*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public JsonResult deleteObject( Integer id) {
		try {
			Integer rows = cusScheduleService.deleteObject(id);
			if(rows !=0 && rows !=null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于id删除课程信息=============错误=================");
		}
		return JsonResult.build(201, "该数据可能已经被删除");
	}
	
	/**基于id查询课程信息*/
	@RequestMapping("/findObjectById")
	@ResponseBody
	public JsonResult findObjectById( Integer id) {
		try {
			CusSchedule cusSchedule = cusScheduleService.findObjectById(id);
			if(cusSchedule != null) {
				return JsonResult.oK(cusSchedule);
			}
		} catch (Exception e) {
			System.out.println("基于id查询课程信息=============错误=================");
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**创建客户课程表*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public JsonResult saveObject( CusSchedule cusSchedule) {
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusSchedule.setUserId(1);
		cusSchedule.setUserParentId(0);
		cusSchedule.setCreatedUser("admin");
		cusSchedule.setModifiedUser("admin");
		try {
			Integer rows = cusScheduleService.saveObject(cusSchedule);
			if(rows != 0 && rows != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("创建客户课程表=============错误=================");
		}
		return JsonResult.build(201, "保存数据错误,请稍后重试");
	}
	
	/**修改课程表数据*/
	@RequestMapping("/updateObject")
	@ResponseBody
	public JsonResult updateObject( CusSchedule cusSchedule) {
		try {
			cusSchedule.setModifiedUser("admin");
			Integer rows = cusScheduleService.updateObject(cusSchedule);
			if(rows != 0 && rows != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("修改课程表数据=============错误=================");
		}
		return JsonResult.build(201, "修改数据错误,请稍后重试");
	}
	
	/**基于客户id查询用户课程表信息*/
	@RequestMapping("/findByCustomerId")
	@ResponseBody
	public JsonResult findByCustomerId( Integer customerId) {
		try {
			List<CusSchedule> list = cusScheduleService.findByCustomerId(customerId);
			if(list.size()!=0 && list != null) {
				return JsonResult.oK(list);
			}
		} catch (Exception e) {
			System.out.println("基于客户id查询用户课程表信息=============错误=================");
		}
		return JsonResult.build(201, "该用户无课程,需添加课程信息");
	}
	
}
