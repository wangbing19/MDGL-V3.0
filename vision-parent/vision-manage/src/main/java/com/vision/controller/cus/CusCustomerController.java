package com.vision.controller.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.cus.CusCustomer;
import com.vision.pojo.cus.vo.CusVo;
import com.vision.pojo.pra.TraInformationrecord;
import com.vision.pojo.rec.RecPayUser;
import com.vision.service.cus.CusCustomerService;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/customer")
public class CusCustomerController {
	
	@Autowired
    private CusCustomerService cusCustomerService;
	
	/**用户页面查看所有信息*/
    @RequestMapping("/findPageObjects")
    @ResponseBody
    public PageObject<CusCustomer> findPageObjects( CusVo cusVo){
    	try {
	    		cusVo.setUserId(1);
				cusVo.setUserParentId(0);
    		 return cusCustomerService.findPageObjects(cusVo);
		} catch (Exception e) {
			System.out.println("用户页面查看所有信息=============错误=================");
		}
        return null;
    }
    
    /**基于客户id查询客户所有信息*/
    @RequestMapping("/findObjectById")
    @ResponseBody
    public CusCustomer findObjectById( Integer id){
    	try {
    		return cusCustomerService.findObjectById(id);
    	} catch (Exception e) {
    		System.out.println("基于客户id查询客户所有信息=============错误=================");
    	}
    	return null;
    }
    
	/**基于用户id修改用户状态*/
	@RequestMapping("updateStateById")
	@ResponseBody
	public Integer doUpdateStateById( CusVo cusVo) {
		try {
			return cusCustomerService.updateStateById(cusVo);
		} catch (Exception e) {
			System.out.println("基于用户id修改用户状态=============错误=================");
		}
		return null;
	}
	
	/**根据咨询表id查询客户表信息有无*/
	@RequestMapping("findConsultationByConsultationId")
	@ResponseBody
	public Integer findConsultationByConsultationId( Integer consultationId) {
		try {
			return cusCustomerService.findConsultationByConsultationId(consultationId);
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return null;
	}
	
	/**将CusCustomer类型数据添加到数据库*/
	@RequestMapping("/saveObject")
	@ResponseBody
	public Integer saveObject( CusCustomer cusCustomer) {
		try {
			return cusCustomerService.saveObject(cusCustomer);
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return null;
	}
	
	/**基于id删除客户信息*/
	@RequestMapping("/deleteObject")
	@ResponseBody
	public Integer deleteObject( Integer id) {
		try {
			return cusCustomerService.deleteObject(id);
		} catch (Exception e) {
			System.out.println("基于id删除客户信息=============错误=================");
		}
		return null;
	}
	
	/**基于客户id修改客户信息*/
	@RequestMapping("/updateObject")
	@ResponseBody
	public Integer updateObject( CusCustomer cusCustomer) {
		try {
			return cusCustomerService.updateObject(cusCustomer);
		} catch (Exception e) {
			System.out.println("根据咨询表id查询客户表信息有无=============错误=================");
		}
		return null;
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
	@RequestMapping("/updateObjectByTimesOfTraining")
	@ResponseBody
	public Integer updateObjectByTimesOfTraining( TraInformationrecord entity) {
		try {
			return cusCustomerService.updateObjectByTimesOfTraining(entity);
		} catch (Exception e) {
			System.out.println("基于训练记录表返回信息更改训练次数=============错误=================");
		}
		return null;
	}
}
