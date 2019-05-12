package com.vision.controller.ppo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.ppo.PpoAppointment;
import com.vision.service.ppo.PpoAppointmentservice;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/appointment")
public class PpoAppointmentController {
	@Autowired
    private PpoAppointmentservice ppoAppointmentservice;
 	
 	/**
	 * 查询所有训练师
	 * @param appointmentName
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult findAll(  String appointmentName,  Integer pageCurrent) {
		try {
			
			PageObject pageObject = ppoAppointmentservice.findAll(appointmentName, pageCurrent);
			
			return JsonResult.oK(pageObject);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return JsonResult.build(201, "查询失败");
		
	}
	
	/**
     * 添加训练师
     * @param ppoAppointment
     * @return
     */
    @RequestMapping("/doSavePpoAppointment")
    @ResponseBody
    public JsonResult doInsertAppointment( PpoAppointment PpoAppointment ) {
    	
    	try {
    		
    		Long  userId =0L;
    		PpoAppointment.setShopId(userId);
    		int saveppoAppointment = ppoAppointmentservice.saveppoAppointment(PpoAppointment);
			return JsonResult.oK(saveppoAppointment);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return JsonResult.build(400, "添加失败");
    }
    
    /**
	 * 修改训练师
	 * 
	 */
	@RequestMapping("/doUpdateAppointment")
	@ResponseBody
	public JsonResult doUpdateAppointment( PpoAppointment ppoAppointment) {
		try {
			Long  userId =0L;
			ppoAppointment.setShopId(userId);
			int result = ppoAppointmentservice.updateAppointment(ppoAppointment);
			
			return JsonResult.oK(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(400, "修改失败");
	}
	/**
	 * 删除训练师
	 * @param ppoAppointment
	 * @return
	 */
	 @RequestMapping("/deleteOne")
	    @ResponseBody
	    public JsonResult dodeleteAppointment(Integer ppoAppointmentId) {
	    	try {
	    		Long  userId =0L;
	    		Integer result=ppoAppointmentservice.dodeleteAppointment(ppoAppointmentId,userId);
	    		return JsonResult.oK(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return JsonResult.build(201, "删除失败");
	    }
}
