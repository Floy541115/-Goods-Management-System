package org.ssmmaven7.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Permission;

public interface IPermissionService {
	public void deletePermissionById(int permissionId);
	public void insertPermission(Permission permission);
	public List<Permission> findAllPermission();
}
