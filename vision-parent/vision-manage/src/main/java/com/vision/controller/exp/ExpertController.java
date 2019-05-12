package com.vision.controller.exp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.exp.Expert;
import com.vision.service.exp.ExpertService;
import com.vision.vo.JsonResult;
import com.vision.vo.Node;
import com.vision.vo.PageObject;

@Controller
@RequestMapping("/expert")
public class ExpertController {
	@Autowired
	private ExpertService expertService;
	
	/** ztree 树显示
	 * 对应远程诊断修改页面中的点击选择专家弹出的专家姓名
	 */
	@RequestMapping("/findZTreeNodes")
	@ResponseBody
	public JsonResult findZTreeNodes(){
		
		Node[] node = expertService.selectExpName();
		ArrayList<Node> list = new ArrayList<>();
		for(int i=0;i<node.length;i++) {
			list.add(node[i]);
		}
		try {
			if(list.size()!=0) {
				return JsonResult.oK(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "没有数据");
	}
	
	
	/**远程诊断分页,基于条件进行分页查询*/
	@RequestMapping("/limitExp")
	@ResponseBody
	public JsonResult limitExp(String expertName,Integer pageCurrent){
		try {
			PageObject<Expert> pageObject = expertService.limitExp(expertName, pageCurrent);
			if(!(pageObject.getRecords().size()==0)) {
				return JsonResult.oK(pageObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"查询无数据");
		
	}
	
	
	/**
	 * 专家表修改通过id显示数据 
	 * 通过选择的id在修改页面获取专家表对应id中的信息
	 */
	@RequestMapping("/selectExp")
	@ResponseBody
	public JsonResult selectExp(Integer id) {
		
		try {
			Expert entity = expertService.selectExp(id);
			if(entity==null) {
				return JsonResult.build(201,"未添加数据");
			}
			return JsonResult.oK(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "该条数据已不存在");
	}
	
	
	/**修改数据
	 * 从浏览器获取修改信息(不包含专家信息这一列)并在数据库进行修改
	 */
	@RequestMapping("/updateExp")
	@ResponseBody
	public JsonResult updateExp( Expert entity) {
		
		try {
			Integer row = expertService.updateExp(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
		
	}
	
	
	/**删除
	 *  从浏览器获取要删除的id并在专家表进行删除
	 * */
	@RequestMapping("/deleteExp")
	@ResponseBody
	public JsonResult deleteExp( Integer... ids) {
		try {
			Integer rows = expertService.deleteExp(ids);
			if(!StringUtils.isEmpty(rows)) {
				return JsonResult.oK("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"删除失败");
	}
	
	
	/**添加
	 * 从浏览器获取添加信息(不包含专家信息这一列)并在专家表进行添加
	 * */
	@RequestMapping("/insertExp")
	@ResponseBody
	public JsonResult insertExp( Expert entity) {
		
		try {
			Integer row = expertService.saveObject(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加失败");
		
	}
	
	/** 添加或修改专家信息 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	public JsonResult updateMessage( Expert entity) {
		try {
			Integer row = expertService.updateMessage(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
	}
}
