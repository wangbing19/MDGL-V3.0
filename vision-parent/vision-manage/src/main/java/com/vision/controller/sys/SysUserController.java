package com.vision.controller.sys;



import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.vision.pojo.sys.Users;
import com.vision.service.sys.UserService;
import com.vision.vo.JsonResult;
import com.vision.vo.Node;
import com.vision.vo.PageObject;


@Controller
@RequestMapping("/user")
//@PropertySource("classpath:/url.properties")
public class SysUserController {
	@Autowired
	private UserService userService;
	//@Value("${sys_user_url}")
	private String sys_url;
//	@Autowired
	private RestTemplate restTemplate;
	
//	@RequiresPermissions("sys:user:view")
	@RequestMapping("doUserListUI.do")
	public String doUserListUI() {
		return "pages/sys/sys_user_list";
	}

	@RequestMapping("doUserEditUI.do")
	public String doUserEditUI() {
		return "pages/sys/sys_user_edit";
	}

	
	//@RequiresLog("用户查询")
	@RequestMapping("doFindPageObjects.do")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		try {
			Users user = new Users();
			PageObject<Users> findPageObjects = userService.findPageObjects(username, pageCurrent,user);
			return JsonResult.oK(findPageObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "账户用户查询失败！");
	}

	@RequestMapping("doSearchPageObjects.do")
	@ResponseBody
	public JsonResult searchPageObjects(String username, Integer pageCurrent) {
		try {
			PageObject<Users> searchPageObjects = userService.searchPageObjects(username, pageCurrent);
			return JsonResult.oK(searchPageObjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "所有账户用户查询失败！");
	}
	/**
	 * 
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("doValidById.do")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid) {
		try {
			String username="admin";
			int doValidById = userService.doValidById(id, valid,username);
			return JsonResult.oK(doValidById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "查询失败！");
	}
	/**
	 * 获取账号节点信息
	 * @return
	 */
	@RequestMapping("doFindZTreeNodes.do")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		try {
			List<Node> findZTreeNodes = userService.findZTreeNodes();
			return JsonResult.oK(findZTreeNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "获取账号节点信息失败！");
	}

	@RequestMapping("doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Users Users) {
		try {
			Users user = new Users();
			int doSaveObject = userService.doSaveObject(user, Users);
			return JsonResult.oK(doSaveObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "保存失败！");
	
	}

	@RequestMapping("doFindObjectById.do")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		try {
			Users doFindObjectById = userService.doFindObjectById(id);
			return JsonResult.oK(doFindObjectById);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return JsonResult.build(201, "此用户信息查询失败！");
	}

	@RequestMapping("doUpdateObject.do")
	@ResponseBody
	public JsonResult doUpdateObject(Users Users) {
		try {
			int doUpdateObject = userService.doUpdateObject(Users);
			return JsonResult.oK(doUpdateObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.build(201, "修改失败！");
		
	}

	private AtomicInteger counter = new AtomicInteger(0);

	//@RequestMapping("doLogin.do")
	@ResponseBody
	public JsonResult doLogin(String username, String password) {
		// 1.封装用户信息
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 2.提交用户信息
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);// 提交给SecurityManager
		int count = counter.incrementAndGet();// count+1;
		return JsonResult.build(200, "登陆成功");
	}
	

}
