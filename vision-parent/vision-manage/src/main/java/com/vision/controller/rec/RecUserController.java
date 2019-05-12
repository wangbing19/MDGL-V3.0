package com.vision.controller.rec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vision.pojo.rec.RecActivityPush;
import com.vision.pojo.rec.RecPayUser;
import com.vision.pojo.sys.Users;
import com.vision.service.cus.CusCustomerService;
import com.vision.service.rec.RecUserService;
import com.vision.vo.JsonResult;


@RestController
@RequestMapping("/recUser")
public class RecUserController {
	@Autowired
	private RecUserService recUserService;
	@Autowired
    private CusCustomerService cusCustomerService;
	
	/** 后台准备查询指定门店下的用户充值记录 */
	@RequestMapping("/findObjectByUserIdAndParentId")
	@ResponseBody
	public JsonResult findObjectByUserIdAndParentId(Users user){
		System.out.println("后台准备查询指定门店下的用户充值记录："+user);
		
//		Users user = ShiroUtils.getUser();
		user.setId(1);
		user.setParentId(0);
		user.setRole(8);
		List<RecPayUser> findObjectList = recUserService.findObjectByUserIdAndParentId(user);
		return JsonResult.oK(findObjectList);
		
	}
	
	/** 根据id查询标题 */
	@RequestMapping("/doFindActivityObjectByUserPayType")
	@ResponseBody
	public JsonResult doFindActivityObjectByUserPayType(Integer id) {
		System.out.println("根据id查询标题");
		RecActivityPush postForObject = recUserService.doFindActivityObjectByUserPayType(id);
		return JsonResult.oK(postForObject);
	}
	
	/** 后台准备保存充值记录 */
	@RequestMapping("/insertObjectRecUser")
	@ResponseBody
	public JsonResult insertObjectRecUser( RecPayUser recPayUser) {
		System.out.println("后台准备保存充值记录:"+recPayUser);
		
//		Users user = ShiroUtils.getUser();
		Users user = new Users();
		user.setId(1);
		user.setParentId(0);
		System.out.println(user);
		if(user!=null) {
			if(user.getId()!=null) {
				long user_id = user.getId();
				recPayUser.setUserId(user_id);
			}
			if(user.getParentId()!=null) {
				long parent_id = user.getParentId();
				recPayUser.setParentId(parent_id);
			}
		}
		String insertMessage = recUserService.insertObjectRecUser(recPayUser);
		
		if(insertMessage.equals("新增记录成功")) {
			cusCustomerService.updateObjectByMoney(recPayUser);
		}
		return JsonResult.oK(insertMessage);
		
	}
}
