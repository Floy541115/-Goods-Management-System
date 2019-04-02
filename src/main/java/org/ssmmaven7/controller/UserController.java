package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IUserService;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.xdevapi.JsonArray;

import util.Consol;
import util.Decode;
import util.JsonUtil;
import util.ValidateCode;

@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value="/login.action", produces = "text/plain;charset=UTF-8")
	public String login(String userName, String userPwd, String roleId) throws Exception{
		// TODO Auto-generated method stub
		/**
		 * 将页面输入信息封装到token，
		 */
		String beforRequestUrl = "" ;
		//不能通过WebUtils.getSavedRequest(request)在任何地方调用来拿到上一个页面的请求。这个方法的调用，更应该是在用户登录成功后，重定向到页面时使用
		// return "redirect:/index";    return "redirect:" + savedRequest.getRequestUrl();
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		if(null != savedRequest){
			beforRequestUrl = savedRequest.getRequestUrl();
		}
		if(beforRequestUrl.equals("") || beforRequestUrl == null){
			beforRequestUrl = "../views/goodViews/allGoods.jsp";   //因为最后是重定向redirect，不是直接return，所以要加.jsp
		}
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd); 
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		try {
			//进入认证
			subject.login(token);
			HttpSession session = request.getSession();
			session.setAttribute("user", subject.getPrincipal());
			return "redirect:"+beforRequestUrl;
		} catch (Exception e) {
			// TODO: handle exception
			beforRequestUrl = "../views/goodViews/allGoods.jsp";
			e.printStackTrace();
			return "redirect:"+ beforRequestUrl;
		}
//		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		
	}
	
 
	@RequestMapping(value = "/register.action", produces = "text/plain;charset=UTF-8")
	public String register(String userName, String userPwd, String email
			, String phone, String address,String roleId) {
		// TODO Auto-generated method stub
		User user = null;
		user = userService.findByPwdNameRoleId(userPwd, userName, Integer.parseInt(roleId));
		if(user == null){
			user = new User();
			user.setAddress(address);
			user.setUserName(userName);
			user.setUserPwd(userPwd);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRoleId(Integer.parseInt(roleId));
			int rs = userService.addUser(user);
			if(rs == 1){
				return "login3";
			}else{
				return "userViews/addUser";
			}
		}else{
			return "userViews/addUser";
		}
	}
	
	@RequestMapping(value = "/allUser.action", produces = "text/plain;charset=UTF-8")
	public String allUser()throws Exception{
		/** layUi动态表格数据结构
		 * {code:xx
		 * msg:xx
		 * count(数据量):xx
		 * 表格数据 data：mapList(map的集合):xx}
		 * 最外层为map 
		 */
		List<User> userList = userService.findAllUser();
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		rsMap.put("count", userList.size());
		Map<String, Object> userMap ;
		List<Map<String, Object>> rsList = new ArrayList<>();
		for(User user : userList){
			userMap = new HashMap<>(); 
			userMap.put("userId", user.getUserId());
			userMap.put("userName", user.getUserName());
			userMap.put("phone", user.getPhone());
			userMap.put("email", user.getEmail());
			userMap.put("address", user.getAddress());
			userMap.put("roleName", user.getRole().getRoleName());
			rsList.add(userMap);
		}
		rsMap.put("data", rsList);    //可以将一个数据的所有信息传给前端，在 前端会根据表头属性获取需要的字段，可以有多余的字段
		String jsonString = JsonUtil.objectToJsonString(rsMap).replaceAll("\\\\", "");
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.write(jsonString); 
		out.flush();
		out.close();
		return "userViews/userList";
	}
	@RequestMapping(value = "/deleteUser.action", produces = "text/plain; charset=UTF-8")
	public String deleteUser(String userId){
		try {
			userService.deleteUser(Integer.parseInt(userId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "userViews/userList";
	}
	
	@RequestMapping(value = "/updateUser.action", produces = "text/plain; charset=UTF-8")
	public String update(String userId, String userName, String phone
			,String email, String address, String roleName){
		userService.updateUser(userName, phone, email, address, Integer.parseInt(userId));
		return "userViews/userList";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(){
		HttpSession session = request.getSession();
		Object o = session.getAttribute("user");
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		try {
			subject.logout();
			session.removeAttribute("user");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "goodViews/allGoods";
	}
}
