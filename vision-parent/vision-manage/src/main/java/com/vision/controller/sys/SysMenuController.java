package com.vision.controller.sys;


import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vision.pojo.sys.Menus;
import com.vision.service.sys.MenuService;
import com.vision.vo.JsonResult;
import com.vision.vo.Node;



@Controller
@RequestMapping("/menu")
public class SysMenuController {
	private String sys_url;
	
	private RestTemplate restTemplate;
	@Autowired
	private MenuService menuService;

	/**
	 * 修改菜单信息
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Menus entity) {
		try {
			int updateObject = menuService.updateObject(entity);
			return JsonResult.oK(updateObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, " 修改菜单信息失败！");
	}
	
	/**
	 *  保存菜单信息
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Menus entity) {
		try {
			int saveObject = menuService.saveObject(entity);
			return JsonResult.oK(saveObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, " 保存菜单信息失败！");
	}
	/**
	 * 查询菜单节点信息
	 * @return
	 */
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		try {
			List<Node> findZtreeMenuNodes = menuService.findZtreeMenuNodes();
			return JsonResult.oK(findZtreeMenuNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, " 查询菜单节点信息！");
		
	}
	
	/**
	 * 删除菜单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		try {
			int deleteObject = menuService.deleteObject(id);
			return JsonResult.oK(deleteObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除菜单信息失败");
	}
	/**
	 * 通过此方法查询所有菜单以及对应的上级菜单信息
	 * @return
	 */
	//@RequiresLog("菜单模块查询")
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		try {
			List<Map<String,Object>> list=menuService.findObjects();
			return JsonResult.oK(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "菜单信息查询！");
	}

}
