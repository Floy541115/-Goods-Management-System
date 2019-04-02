package org.ssmmaven7.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.dao.RolePermissionDao;
import org.ssmmaven7.model.RolePermission;
import org.ssmmaven7.service.IRolePermissionService;
@Service
public class RolePermissionService implements IRolePermissionService{
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Override
	public Set<String> findPermissionCodeByRoleId(int roleId) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set = rolePermissionDao.findPermissionCodeByRoleId(roleId);
		return set;
	}

	@Override
	public void insertRolePermission(RolePermission rolePermission) {
		// TODO Auto-generated method stub
		rolePermissionDao.insertRolePermission(rolePermission);
	}

	@Override 
	public void deleteRolePermissionByRoleId(int roleId) {
		// TODO Auto-generated method stub
		rolePermissionDao.deleteRolePermissionByRoleId(roleId);
	}

	@Override
	public Set<String> findPermissionUrlByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return rolePermissionDao.findPermissionUrlByRoleId(roleId);
	}

	@Override
	public void deleteRolePermissionByRPId(int roleId, int permissionId) {
		// TODO Auto-generated method stub
		rolePermissionDao.deleteRolePermissionByRPId(roleId, permissionId);
	}
	
}
