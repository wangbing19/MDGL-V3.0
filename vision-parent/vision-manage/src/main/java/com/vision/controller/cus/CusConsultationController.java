package com.vision.controller.cus;

import com.vision.pojo.cus.CusConsultation;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.service.cus.CusConsultationService;
import com.vision.vo.PageObject;
import com.vision.vo.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cusConsultation")
public class CusConsultationController {

    @Autowired
    private CusConsultationService cusConsultationService;

	/* 点击跳转诊断页面 */
    @RequestMapping("/doCusConsultationListUI")
    public String doCusConsultationListUI(){
        return "pages/sys/cusConsultation_list";
    }
    
    
    /**跳转到修改或新增信息*/
	@RequestMapping("/doCusConsultationEditUI")
	public String doCusConsultationEditUI() {
		return "pages/sys/cusConsultation_edit";
	}
    
    
    /**基于用户/电话及当前页码值条件查询用户信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public JsonResult findPageObjects( CusVo cusVo){
    	cusVo.setUserId(1);
		cusVo.setUserParentId(0);
    	try {
    		PageObject<CusConsultation> pageObject = cusConsultationService.findPageObjects(cusVo);
    		if(!(pageObject.getRecords().size()==0)) {
        		return JsonResult.oK(pageObject);
        	}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("基于用户/电话及当前页码值条件查询用户信息=============错误=================");
		}
    	
    	return JsonResult.build(201, "查询无数据");
    }
    
    /**将CusCustomer类型数据添加到数据库*/
    @RequestMapping("/saveObject")
    @ResponseBody
    public JsonResult saveObject( CusConsultation cusConsultation) {
    	//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusConsultation.setUserId(1);
		cusConsultation.setUserParentId(0);
		cusConsultation.setCreatedUser("admin");
		cusConsultation.setModifiedUser("admin");
    	try {
			Integer row = cusConsultationService.saveObject(cusConsultation);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("将CusCustomer类型数据添加到数据库=============错误==================");
		}
    	return JsonResult.build(201, "保存失败");
    }
    
	/**基于id删除咨询表信息*/
	@RequestMapping("deleteObject")
	@ResponseBody
	public JsonResult deleteObject(Integer id) {
		try {
			Integer row = cusConsultationService.deleteObject(id);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于id删除咨询表信息=============错误==================");
		}
		return JsonResult.build(201, "删除失败,数据或已删除");
	}
    
	/**基于咨询表id,查询相关id所有信息*/
	@RequestMapping("findObjectById")
	@ResponseBody
	public JsonResult findObjectById(Integer id) {
		try {
			CusConsultation cusConsultation = cusConsultationService.findObjectById(id);
			if(cusConsultation != null) {
				return JsonResult.oK(cusConsultation);
			}
		} catch (Exception e) {
			System.out.println("基于咨询表id,查询相关id所有信息=============错误==================");
		}
		return JsonResult.build(201, "修改查询失败");
	}
	

	/**基于咨询表id更改用户信息*/
	@RequestMapping("updateObject")
	@ResponseBody
	public JsonResult doUpdateObject(CusConsultation cusConsultation) {
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusConsultation.setUserId(1);
		cusConsultation.setUserParentId(0);
		try {
			Integer row = cusConsultationService.updateObject(cusConsultation);
			if(row != 0 || row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于咨询表id更改用户信息=============错误==================");
		}
		return JsonResult.build(201, "修改保存失败");
	}

}
