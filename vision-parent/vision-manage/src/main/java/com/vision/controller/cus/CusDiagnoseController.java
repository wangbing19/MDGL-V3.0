package com.vision.controller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.cus.CusDiagnose;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.service.cus.CusDiagnoseService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;


@Controller
@RequestMapping("/cusDiagnose")
public class CusDiagnoseController {
	
	@Autowired
	private CusDiagnoseService cusDiagnoseService;
	
	
	/**点击跳转用户页面*/
	@RequestMapping("/doCusDiagnoseListUI")
	public String doCusConsultationListUI() {
		return "pages/sys/cusDiagnose_list";
	}
	
	/**诊断表修改页面跳转*/
	@RequestMapping("/doCusDiagnoseEditUI")
	public String doCusDiagnoseEditUI() {
		return "pages/sys/cusDiagnose_edit";
	}
	
	/**诊断表页面加载,查询*/
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(CusVo cusVo){
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusVo.setUserId(1);
		cusVo.setUserParentId(0);
		try {
			PageObject<CusDiagnose> pageObject = cusDiagnoseService.findPageObjects(cusVo);
			if(pageObject.getRecords().size()!=0) {
				return JsonResult.oK(pageObject);
			}
			
		} catch (Exception e) {
			System.out.println("诊断表页面加载,查询=============错误=================");
		}
		return JsonResult.build(201, "查询无数据");
	}
	
	/**基于咨询表id,查询相关id所有信息*/
	@RequestMapping("/findObjectById")
	@ResponseBody
	public JsonResult findObjectById(Integer id) {
		try {
			CusDiagnose cusDiagnose = cusDiagnoseService.findObjectById(id);
			if(cusDiagnose != null) {
				return JsonResult.oK(cusDiagnose);
			}
		} catch (Exception e) {
			System.out.println("基于咨询表id,查询相关id所有信息=============错误=================");
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**基于客户id查询诊断表相关信息*/
	@RequestMapping("/findByCustomerId")
	@ResponseBody
	public JsonResult findByCustomerId(Integer customerId) {
		try {
			CusDiagnose cusDiagnose = cusDiagnoseService.findByCustomerId(customerId);
			if(cusDiagnose != null) {
				return JsonResult.oK(cusDiagnose);
			} else if(cusDiagnose == null){
				return JsonResult.build(203, "无数据,需新增数据");
			}
		} catch (Exception e) {
			System.out.println("基于客户id查询诊断表相关信息=============错误=================");
		}
		return JsonResult.build(201, "修改查询数据错误");
	}
	
	/**基于客户id创建客户诊断表*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public JsonResult saveObject(CusDiagnose cusDiagnose) {
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusDiagnose.setCreatedUser("admin");
		cusDiagnose.setModifiedUser("admin");
		cusDiagnose.setUserId(1);
		cusDiagnose.setUserParentId(0);
		try {
			Integer row = cusDiagnoseService.saveObject(cusDiagnose);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于客户id创建客户诊断表=============错误=================");
		}
		return JsonResult.build(201, "新增诊断表错误");
	}
	
	/**基于诊断表id删除数据*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public JsonResult deleteObject(Integer id) {
		try {
			Integer row = cusDiagnoseService.deleteObject(id);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于诊断表id删除数据=============错误=================");
		}
		return JsonResult.build(201, "数据可能已删除,请刷新");
	}
	
	
}
