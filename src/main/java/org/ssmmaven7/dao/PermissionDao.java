package org.ssmmaven7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Permission;

public interface PermissionDao {
	public void deletePermissionById(@Param("permissionId")int permissionId);
	public void insertPermission(Permission permission);
	public List<Permission> findAllPermission();
}
