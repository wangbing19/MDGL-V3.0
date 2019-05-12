package com.vision.controller.sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vision.pojo.sys.Logs;
import com.vision.service.sys.LogService;
import com.vision.vo.JsonResult;
import com.vision.vo.PageObject;
import com.vision.vo.sys.RestTemplateParmas;



@Controller
@RequestMapping("/log")
public class SysLogController {
	
	@Autowired
	private LogService logService;
	private String sys_url;
   // @Autowired
   // private RestTemplate restTemplate;
    


	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("doDeleteObjects.do") // (只能处理post请求)
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {
		try {
			int deleteObjects = logService.deleteObjects(ids);
			return JsonResult.oK(deleteObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "删除失败！");
	}

	/**
	 * 查询数据
	 * 
	 * @param username
	 * @param page
	 * @return
	 */
	//@RequiresLog("日志查询")
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects( RestTemplateParmas RestTemplateParmas) {
		try {
			String username = RestTemplateParmas.getName();
			Integer pageCurrent = RestTemplateParmas.getPageCurrent();
			PageObject<Logs> pageObject = logService.findPageObjects(username, pageCurrent);
			return  JsonResult.oK(pageObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "日志查询失败，请稍后查询");
	}

}
