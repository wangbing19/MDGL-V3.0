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

import com.vision.pojo.sys.Depts;
import com.vision.service.sys.DeptService;
import com.vision.vo.JsonResult;
import com.vision.vo.Node;

@Controller
@RequestMapping("/dept")
//@PropertySource("classpath:/url.properties")
public class SysDeptController {
	
	//@Value("${sys_dept_url}")
	private String sys_url;
    //@Autowired
    private RestTemplate restTemplate;
    @Autowired 
    DeptService sysDeptService;
    /**
     *
     * @return 组织管理--加载页面
     */
    
	//@RequiresPermissions("sys:dept:view")
    @RequestMapping("/doDeptListUI")
    public String doDeptListUI() {
        return "pages/sys/sys_dept_list";
    }
    /**
     * 加载编辑页面
     * @return
     */
	//@RequiresPermissions("sys:dept:add")
    @RequestMapping("/doDeptEditUI")
    public String doDeptEditUI() {
        return "pages/sys/sys_dept_edit";
    }
	//@RequiresLog("组织管理查询")
    @RequestMapping("/doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
    	try {
    		List<Map<String, Object>> findObjects = sysDeptService.findObjects();
    		return JsonResult.oK(findObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return JsonResult.build(201, "组织管理查询失败！");
    }
    
    
    /**
	 * 查询组织结构数据
	 * @return
	 */
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		try {
			List<Node> findZTreeNodes = sysDeptService.findZTreeNodes();
			return JsonResult.oK(findZTreeNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询组织结构数据失败！");
		
	}
	/**
	 * 新增保存
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Depts entity){
		try {
			int saveObject = sysDeptService.saveObject(entity);
			return JsonResult.oK(saveObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "组织管理新增失败！");
	}
	
	/**
	 * 更新
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Depts entity){
		try {
			int updateObject = sysDeptService.updateObject(entity);
			return JsonResult.oK(updateObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "组织管理更新失败！");
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	//@RequiresPermissions("sys:dept:delete")
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		try {
			int deleteObject = sysDeptService.deleteObject(id);
			return JsonResult.oK(deleteObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "组织管理删除失败！");
	}

}
