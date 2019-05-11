package com.vision.controller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.cus.CusCustomer;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.pojo.rec.RecPayUser;
import com.vision.pojo.sys.Users;
import com.vision.pojo.tra.TraInformationrecord;
import com.vision.service.cus.CusCustomerService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/customer")
public class CusCustomerController {
	
	@Autowired
    private CusCustomerService cusCustomerService;
	
	/**点击跳转用户页面*/
	@RequestMapping("/doCustomerListUI")
	public String doCustomerListUI() {
		return "pages/sys/customer_list";
	}
	
	/**跳转到新增或修改信息*/
	@RequestMapping("/doCustomerEditUI")
	public String doCustomerEditUI() {
		return "pages/sys/customer_edit";  
	}
	
	/**用户页面查看所有信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public JsonResult findPageObjects( CusVo cusVo){
    	//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusVo.setUserId(1);
		cusVo.setUserParentId(0);
    	try {
			PageObject<CusCustomer> pageObject = cusCustomerService.findPageObjects(cusVo);
			if(pageObject.getRecords().size()!=0) {
				return JsonResult.oK(pageObject);
			}
		} catch (Exception e) {
			System.out.println("用户页面查看所有信息=============错误=================");
		}
    	return JsonResult.build(201, "查询无数据");
    }
    
    /**基于客户id查询客户所有信息*/
    @RequestMapping("/findObjectById")
    @ResponseBody
    public JsonResult findObjectById( Integer id){
    	try {
    		CusCustomer cusCustomer = cusCustomerService.findObjectById(id);
			if(cusCustomer != null) {
				return JsonResult.oK(cusCustomer);
			}
    	} catch (Exception e) {
    		System.out.println("基于客户id查询客户所有信息=============错误=================");
    	}
    	return JsonResult.build(201, "修改查询失败");
    }
    
	/**基于用户id修改用户状态*/
	@RequestMapping("updateStateById")
	@ResponseBody
	public JsonResult doUpdateStateById( CusVo cusVo) {
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusVo.setUser("admin");
		try {
			Integer row = cusCustomerService.updateStateById(cusVo);
			if(row != 0 && row != null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于用户id修改用户状态=============错误=================");
		}
		return JsonResult.build(201, "状态修改失败");
	}
	
	/**根据咨询表id查询客户表信息有无*/
	@RequestMapping("findConsultationByConsultationId")
	@ResponseBody
	public JsonResult findConsultationByConsultationId( Integer consultationId) {
		try {
			Integer row = cusCustomerService.findConsultationByConsultationId(consultationId);
			if(row == 0 || row == 1) {
				return JsonResult.oK(row);
			}
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return JsonResult.build(201, "已有数据,无法添加");
	}
	
	/**将CusCustomer类型数据添加到数据库*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public JsonResult saveObject( CusCustomer cusCustomer) {
		try {
			//获取登录用户信息
//        	Users Users = ShiroUtils.getUser();
        	//获取登录用户创建客户数量
			
//			 if(Users.getDeptNum()>=Users.getDeptLimit()) {
//				 return JsonResult.build(201,"创建客户数量已达上限,无法再次创建客户,请联系总店208-62825475");
//			 }
			cusCustomer.setCreatedUser("admin");
			cusCustomer.setModifiedUser("admin");
			cusCustomer.setUserId(1);
			cusCustomer.setUserParentId(0);
			
			Integer row = cusCustomerService.saveObject(cusCustomer);
			if(row != 0 && row != null) {
				  //添加登录用户创建客户数量 
//				Users.setDeptNum(Users.getDeptNum()+1);
//				  restTemplate.postForObject("http://176.198.114.212.:8029/user/doUpdateObject", Users, JsonResult.class);//176.198.114.212
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return JsonResult.build(201, "此客户可能已存在");
	}
	
	/**基于id删除客户信息*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public JsonResult deleteObject( Integer id) {
		try {
			Integer row = cusCustomerService.deleteObject(id);
			if(row != 0 && row == null) {
				return JsonResult.oK();
			}
		} catch (Exception e) {
			System.out.println("基于id删除客户信息=============错误=================");
		}
		return JsonResult.build(201, "此客户可能已经不存在");
	}
	
	/**基于客户id修改客户信息*/
	@RequestMapping("/updateObject")
	@ResponseBody
	public JsonResult updateObject( CusCustomer cusCustomer) {
		//获取登录用户信息
//    	Users user = ShiroUtils.getUser();
		cusCustomer.setModifiedUser("admin");
		try {
			Integer row = cusCustomerService.updateObject(cusCustomer);
			if(row != 0 && row == null) {
				return JsonResult.oK();
			}
			
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return JsonResult.build(201, "此客户信息修改失败");
	}
	
	/**基于充值记录表返回信息用户id修改金额,余额及充值次数*/
	@RequestMapping("/updateObjectByMoney")
	@ResponseBody
	public Integer updateObjectByMoney( RecPayUser recPayUser) {
		try {
			return cusCustomerService.updateObjectByMoney(recPayUser);
		} catch (Exception e) {
			System.out.println("基于充值记录表返回信息用户id修改金额,余额及充值次数=============错误=================");
		}
		return null;
	}
	
	/**基于训练记录表返回信息更改训练次数*/
//	@RequestMapping("/updateObjectByTimesOfTraining")
//	@ResponseBody
//	public Integer updateObjectByTimesOfTraining( TraInformationrecord entity) {
//		try {
//			return cusCustomerService.updateObjectByTimesOfTraining(entity);
//		} catch (Exception e) {
//			System.out.println("基于训练记录表返回信息更改训练次数=============错误=================");
//		}
//		return null;
//	}
}
