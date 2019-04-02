package org.ssmmaven7.service;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.RolePermission;

public interface IRolePermissionService {
	public Set<String> findPermissionCodeByRoleId(int roleId);
	public Set<String> findPermissionUrlByRoleId(int roleId);
	public void insertRolePermission(RolePermission rolePermission);
	public void deleteRolePermissionByRoleId(int roleId);
	public void deleteRolePermissionByRPId(int roleId, int permissionId);
}
