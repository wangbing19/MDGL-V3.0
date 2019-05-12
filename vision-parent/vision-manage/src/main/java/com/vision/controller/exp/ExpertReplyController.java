package com.vision.controller.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.pojo.exp.ExpertReply;
import com.vision.service.exp.ExpertReplyService;
import com.vision.vo.JsonResult;

@Controller
@RequestMapping("/expertReply")
public class ExpertReplyController {
	
	@Autowired
	private ExpertReplyService expertReplyService;
	
	/**
	 * 通过前端获取远程诊断表remoteDiagnoseId并查询出remoteDiagnoseId对应的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectRep")
	@ResponseBody
	public JsonResult selectRep(Integer id) {
		try {
			ExpertReply selectRep = expertReplyService.selectRep(id);
			return JsonResult.oK(selectRep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "当前数据不存在");
	}
	
	/**
	 * 从浏览器获取添加信息在专家回复表进行添加
	 * @param entity
	 * @return
	 */
	@RequestMapping("/insertRep")
	@ResponseBody
	public JsonResult insertRep(ExpertReply entity) {
		try {
			Integer row = expertReplyService.insertRep(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"添加失败");
	}
	
	
	/**删除*/
	/**
	 * 从浏览器获取要删除的<远程诊断表id>并在专家回复表进行删除
	 * @param id
	 * @return
	 */
//	@RequestMapping("/deleteRep")
//	@ResponseBody
//	public JsonResult deleteRep(Integer... id) {
//		try {
//			Integer row = expertReplyService.doDeleteRep(id);
//			if(!StringUtils.isEmpty(row)) {
//				return JsonResult.oK("删除成功");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return JsonResult.build(201,"删除失败");
//	}
	
	/**
	 * 从浏览器获取要修改的信息并在专家回复表进行修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("/updateRep")
	@ResponseBody
	public JsonResult updateRep(ExpertReply entity) {
		try {
			Integer row = expertReplyService.updateRep(entity);
			if(!StringUtils.isEmpty(row)) {
				return JsonResult.oK("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201,"修改失败");
	}
}
