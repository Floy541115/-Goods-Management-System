package org.ssmmaven7.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.RolePermission;

public interface RolePermissionDao {
	public Set<String> findPermissionCodeByRoleId(@Param("roleId")int roleId);
	public Set<String> findPermissionUrlByRoleId(@Param("roleId")int roleId);
	public void insertRolePermission(RolePermission rolePermission);
	public void deleteRolePermissionByRoleId(@Param("roleId")int roleId);
	public void deleteRolePermissionByRPId(@Param("roleId")int roleId, @Param("permissionId")int permissionId);
}
