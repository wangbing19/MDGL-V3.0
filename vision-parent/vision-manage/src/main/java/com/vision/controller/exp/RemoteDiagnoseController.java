package com.vision.controller.exp;

import com.vision.pojo.exp.RemoteDiagnose;
import com.vision.pojo.exp.vo.ExpRemoteDiagnoseVo;
import com.vision.service.exp.RemoteDiagnoseService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/remoteDiagnose")
public class RemoteDiagnoseController {

	@Autowired
	private RemoteDiagnoseService remoteDiagnoseService;

	/**
	 * 远程诊断分页
	 * 对应前端页面远程诊断模块呈现页面,
	 * 从前端获取当前页码值并的开始下标返回指定长度的信息,
	 * 获取输入框中customerName的名字,没有则查询全部
	 * 并在数据库进行分页查询返回查询的信息
	 * @param customerName
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(String customerName,Integer pageCurrent){
		
		// 获取登录用户的账号
//		Users user=ShiroUtils.getUser(); 
		Integer parentId = 0;//user.getParentId();
		try {
			PageObject<ExpRemoteDiagnoseVo> postForObject = remoteDiagnoseService.findPageObjects(customerName, pageCurrent, parentId);
			if(!(postForObject.getRecords().size()==0)) {
				return JsonResult.oK(postForObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  JsonResult.build(201, "查询无数据");
	}

	/**
	 * 远程诊断修改
	 * 通过选择的id在修改页面获取远程诊断表对应id中的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/select")
	@ResponseBody
	public JsonResult select(Integer id) {
		try {
			ExpRemoteDiagnoseVo entity = remoteDiagnoseService.select(id);
			return JsonResult.oK(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}
	
	/**
	 * 修改解决状态
	 * 显示是否解决的点击事件切换
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("/validById")
	@ResponseBody
	public JsonResult validById(Integer id,Integer valid) {
		
//		Users user=ShiroUtils.getUser();
		String modifyuser="admin";//user.getUsername();//获取修改时用户的账号名(添加时默认是自身账号)
		try {
			Integer row = remoteDiagnoseService.validById(id,valid,modifyuser);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("切换成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "切换失败");
		
	}
	
	/**
	 * 删除:从前端获取id,通过id从数据库删除对应的数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete( Integer... ids) {
		try {
			Integer rows = remoteDiagnoseService.delete(ids);
			if(!StringUtils.isEmpty(rows)) {
				return JsonResult.oK("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "数据已不存在");
	}
	
	/**对应前端用户管理模块中的添加作用
	 *将从前端获取的用户信息和角色信息添加到数据库
	 */
	@RequestMapping("/saveObject")
	@ResponseBody
	public JsonResult saveObject(RemoteDiagnose entity) {
		
//		Users user=ShiroUtils.getUser();
//		String loginuser= user.getUsername();//获取添加时用户的账号名
//		String modifyuser= user.getUsername();//获取修改时用户的账号名(添加时默认是自身账号)
//		Integer parentId= user.getParentId();
		entity.setRegisterUser("admin");
		entity.setModifiedUser("admin");
		entity.setRegisterParentid(0);
		entity.setUserId(1);
		entity.setUserParentId(0);
		
		try {
			Integer row = remoteDiagnoseService.saveObject(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "添加失败");
		
	}
	
	/**
	 * 获取修改后的数据传入数据库
	 * @param entity
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(RemoteDiagnose entity) {
//		Users user = ShiroUtils.getUser();
//		String modifyuser = user.getUsername();
		entity.setModifiedUser("admin");
		try {
			Integer row = remoteDiagnoseService.update(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败");
	}
}
