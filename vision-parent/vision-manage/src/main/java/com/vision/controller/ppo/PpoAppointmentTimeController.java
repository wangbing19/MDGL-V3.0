package com.vision.controller.ppo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.ppo.PpoAppointmentTime;
import com.vision.service.ppo.PpoAppointmentTimeService;
import com.vision.vo.JsonResult;

@Controller
@RequestMapping("/time")
public class PpoAppointmentTimeController {
	@Autowired
	PpoAppointmentTimeService ppoAppointmentTimeService;
	
	/**
	 * 添加训练师训练时间
	 * @param ppoAppTime
	 * @return
	 */
	@RequestMapping("/doinsertAppointmentTime")
	@ResponseBody
	public JsonResult doinsertAppointmentTime(PpoAppointmentTime ppoAppointmentTime) {
		try {
		
			Integer result = ppoAppointmentTimeService.doInsertAppointment(ppoAppointmentTime);
			
				return JsonResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存失败");
	}
	
	//查询训练师时间
		@RequestMapping("/dofindAppointmentTime")
		@ResponseBody
		public JsonResult dofindAppointmentTime( Integer ppoAppointmentId) {
			try {
				
				List<PpoAppointmentTime> result = ppoAppointmentTimeService.dofindappointmentTime(ppoAppointmentId);
				return JsonResult.oK(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonResult.build(201, "查询失败");
		}
		
			
		
}
