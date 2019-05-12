package com.vision.controller.sys;



import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.pojo.sys.Roles;
import com.vision.service.sys.RoleService;
import com.vision.vo.CheckBox;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;
import com.vision.vo.sys.RestTemplateParmas;
import com.vision.vo.sys.SysRoleMenuResult;


@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private RoleService roleService;
	private String sys_url;

	//@Autowired
	//private RestTemplate restTemplate;



	/**
	 * 查询所有角色信息
	 * @return
	 */
	@RequestMapping("/doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects() {
		try {

			List<CheckBox> findObjects = roleService.findObjects();
			return JsonResult.oK(findObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询所有角色信息失败");
	}
	/**
	 * 基于id查询角色以及对应的菜单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {

		try {
			SysRoleMenuResult findObjectById = roleService.findObjectById(id);
			return JsonResult.oK(findObjectById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "基于id查询角色以及对应的菜单信息失败！");
	}
	/**
	 * 保存角色和菜单关系数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Roles entity, Integer[] menuIds) {
		try {
			int updateObject = roleService.updateObject(entity, menuIds);
			return JsonResult.oK(updateObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, " 保存角色和菜单失败！");

	}
	/**
	 * 保存角色自身信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Roles entity, Integer[] menuIds) {
		try {
			int saveObject = roleService.saveObject(entity, menuIds);
			return JsonResult.oK(saveObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存角色信息失败！");
	}

	/**
	 * 删除角色自身信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Long id) {
		try {
			int deleteObject = roleService.deleteObject(id);
			return JsonResult.oK(deleteObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除角色失败！");
	}

	/**
	 * 依据条件查询角色当前页信息
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		try {
			PageObject<Roles> findPageObjects = roleService.findPageObjects(name, pageCurrent);
			return JsonResult.oK(findPageObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "依据条件查询角色当前页信息失败！");
	}

}
