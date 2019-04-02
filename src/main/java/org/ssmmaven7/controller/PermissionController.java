package org.ssmmaven7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.model.Permission;
import org.ssmmaven7.service.IPermissionService;

import util.JsonUtil;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping(value = "/insertPermission", produces = "text/plain; charset=UTF-8")
	public String insertPermission(String url, String description){
		Permission permission = new Permission();
		permission.setDescription(description);
		permission.setUrl(url);
		try {			
			permissionService.insertPermission(permission);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "rp/role-pms";
	}
	
	@RequestMapping(value = "/deletePermission", produces = "text/plain; charset=UTF-8")
	public String deletePermissionById(String permissionId){
		try {
			permissionService.deletePermissionById(Integer.parseInt(permissionId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "rp/role-pms";
	}
	
	@RequestMapping(value = "/findAllPermission", produces = "text/plain; charset=UTF-8")
	public String findAllPermission(){
		List<Permission> permissions = new ArrayList<Permission>();
		try {
			permissions = permissionService.findAllPermission();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JsonUtil.objectToJsonString(permissions).replaceAll("\\\\", "");
	}
}
