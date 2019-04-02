package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IRedisService;
import org.ssmmaven7.service.IRoleService;
import org.ssmmaven7.service.IUserService;
import org.ssmmaven7.service.impl.RedisService;

import com.alibaba.fastjson.JSONArray;

import util.Consol;
import util.JsonUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRedisService redisService;
	@Autowired
	private IUserService userService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value="/allRoles.action")
	public String allRoles()throws Exception{
		String rsString = null;
		try {
			rsString = roleService.findAllRole();
			//注意：不管有没有执行SQL查找，这个list都是从aspect传过来
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(rsString);
		out.flush();
		out.close();
		Consol.log(rsString);
	
		return "user/manageUser";
	}
	
	@RequestMapping(value="/deleteRole.action")
	public String deleteRole(String roleIdString){
		int roleId = Integer.parseInt(roleIdString);
		Set<User> users = new HashSet<>(); 
		try {
			users = userService.findUserByRoleId(roleId);
			roleService.deleteByRoleId(roleId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JsonUtil.objectToJsonString(users).replaceAll("\\\\", "");
	}
}