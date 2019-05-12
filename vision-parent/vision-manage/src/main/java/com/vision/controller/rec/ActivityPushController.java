package com.vision.controller.rec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vision.pojo.rec.RecActivityPush;
import com.vision.service.rec.ActivityPushService;
import com.vision.vo.JsonResult;


@RestController
@RequestMapping("/activity")
public class ActivityPushController {
	@Autowired
	private ActivityPushService activityPushService;
	
	@RequestMapping("/FindPageObjects")
	@ResponseBody
	public JsonResult findObjects(Integer activityState){
		
		
		System.out.println("后台查询数据初始化页面");
		List<RecActivityPush> findObjects = activityPushService.findObjects(activityState);
		return JsonResult.oK(findObjects);
		
	}
	
	@RequestMapping("/deleteObjectById")
	@ResponseBody
	public JsonResult deleteObjectById(Integer id) {
		
		System.out.println("后台准备删除数据"+id);
		String deleteMessage = activityPushService.deleteObjectById(id);
		return JsonResult.oK(deleteMessage);
		
		 
	}
	
	@RequestMapping("/insertRecActivityObject")
	@ResponseBody
	public JsonResult insertRecActivityObject( RecActivityPush recActivityPush) {
		
		System.out.println("后台准备入库："+recActivityPush);
		String insertMessage = activityPushService.insertRecActivityObject(recActivityPush);
		return JsonResult.oK(insertMessage);
		
	}
	@RequestMapping("/updateRecActivityObject")
	@ResponseBody
	public JsonResult updateRecActivityObject( RecActivityPush recActivityPush) {
		System.out.println("后台修改");
		String updateMessage = activityPushService.updateRecActivityObject(recActivityPush);
		return JsonResult.oK(updateMessage);
	}
	
	@RequestMapping("/findPageObjectByTitle")
	@ResponseBody
	public JsonResult findPageObjectByTitle(String title) {
		System.out.println("后台转呗查询数据，标题名称："+title);
		RecActivityPush object = activityPushService.findPageObjectByTitle(title);
		return JsonResult.oK(object);
	}
	
}
