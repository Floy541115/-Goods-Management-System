package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.model.Permission;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.model.RolePermission;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IPermissionService;
import org.ssmmaven7.service.IRedisService;
import org.ssmmaven7.service.IRolePermissionService;
import org.ssmmaven7.service.IRoleService;
import org.ssmmaven7.service.IUserService;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import util.Consol;
import util.JsonUtil;

@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController {
	@Autowired
	private IRolePermissionService rolePermissionService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRedisService redisService;
	@Autowired
	private IUserService userService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping(value = "/deleteRolePermissionByRoleId", produces = "text/plain; charset=UTF-8")
	public String deleteRolePermissionByRoleId(String roleId){
		try {
			rolePermissionService.deleteRolePermissionByRoleId(Integer.parseInt(roleId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/userViews/userInfo";
	}
	
	@RequestMapping(value = "/deleteRolePermissionByRPId", produces="text/plain; charset=UTF-8")
	public String deleteRolePermissionByRPId(String roleId, String permissionId){
		try {
			rolePermissionService.deleteRolePermissionByRPId(Integer.
					parseInt(roleId), Integer.parseInt(permissionId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/userViews/userInfo";
	}
	
	@RequestMapping(value = "/insertRolePermission", produces ="text/plain; charset=UTF-8")
	public String insertRolePermission(String roleId, String permissionId){
		RolePermission rolePermission = new RolePermission();
		rolePermission.setPermissionId(Integer.parseInt(permissionId));
		rolePermission.setRoleId(Integer.parseInt(roleId));
		try {
			rolePermissionService.insertRolePermission(rolePermission);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
	
		}
		return "/userViews/userInfo";
	}
	
	@RequestMapping(value = "/findPermissionCodeByRoleId")
	public Set<String> findPermissionCodeByRoleId(String roleId){
		Set<String> set = new HashSet<>();
		try {
			set = rolePermissionService.findPermissionCodeByRoleId(Integer.parseInt(roleId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return set;
		//shiro.xml小众添加请求地址的权限，为之前的controller添加最外层requestMapping地址，修改返回地址，修改页面请求地址，添加权限页面
	}
	@RequestMapping(value = "/findAllRoleAndPms")
	public String findAllRoleAndPms(){
		java.util.Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", 0);
		resultMap.put("msg", "");
		Set<Role> roleSet = new HashSet<>();
		List<java.util.Map<String, Object>> roleMapList = new ArrayList<>();
		java.util.Map<String, Object> roleMap ;
		try {
			roleSet = roleService.findAllRoleAndPms();
			for(Role role : roleSet){
				String roleId = role.getRoleId()+"";
				String roleName = role.getRoleName();
//				roleMap = new HashMap<>();
//				roleMap.put("roleId", roleId);
//				roleMap.put("roleName", roleName);
//				roleMap.put("permCount", role.getPermissions().size());
				for(Permission permission : role.getPermissions()){
					roleMap = new HashMap<>();
					roleMap.put("deleteRole", roleId);
					roleMap.put("roleId", roleId);
					roleMap.put("roleName", roleName);
					roleMap.put("permissionId", permission.getPermissionId()+""); 
					roleMap.put("resourceCode", permission.getResourceCode());
					roleMap.put("description", permission.getDescription());
					roleMapList.add(roleMap);
				}
			}
			resultMap.put("count", roleMapList.size());
			resultMap.put("data", roleMapList);
			String jsonString = JsonUtil.objectToJsonString(resultMap).replaceAll("\\\\", "");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.write(jsonString);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		return "rolePermission";  //如果直接返回resultMap的json字符串，前端ajax回调函数参数为空
	}
	
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
	
		return "/userViews/manageUser";
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
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "0");
			return jsonObject.toJSONString().replaceAll("\\\\", "");
		}
		return JsonUtil.objectToJsonString(users).replaceAll("\\\\", "");
	}
	
	@RequestMapping(value="insertRole")
	public String insertRole(String roleName){
		Role role = new Role();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			role.setRoleName(roleName);
			roleService.insertRole(role); 
			out.write("success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.write("faild");
		}finally{
			out.flush();
			out.close();
		}
		return "rolePermission";
	}
	
	@RequestMapping(value = "/insertPermission", produces = "text/plain; charset=UTF-8")
	public String insertPermission(String resourceCode, String description)throws Exception{
		Permission permission = new Permission();
		permission.setDescription(description);
		permission.setUrl(resourceCode);
		PrintWriter out = response.getWriter();
		try {			
			permissionService.insertPermission(permission);
			out.write("success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.write("faild");
		}finally{
			out.flush();
			out.close();
		}
		return "rolePermission";
	}
	
	@RequestMapping(value = "/deletePermission", produces = "text/plain; charset=UTF-8")
	public String deletePermissionById(String permissionId){
		try {
			permissionService.deletePermissionById(Integer.parseInt(permissionId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "rolePermission";
	}
	
	@RequestMapping(value = "/findAllPermission", produces = "text/plain; charset=UTF-8")
	public String findAllPermission(){
		List<Permission> permissions = new ArrayList<Permission>();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			permissions = permissionService.findAllPermission();
			out.write(JsonUtil.objectToJsonString(permissions).replaceAll("\\\\", ""));
		} catch (Exception e) { 
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "rolePermission";
	}
}
