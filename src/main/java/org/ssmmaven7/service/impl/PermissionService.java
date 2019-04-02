package org.ssmmaven7.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.dao.PermissionDao;
import org.ssmmaven7.model.Permission;
import org.ssmmaven7.service.IPermissionService;
@Service
public class PermissionService implements IPermissionService{
	@Autowired
	private PermissionDao permissionDao;
	@Override
	public void deletePermissionById(int permissionId) {
		// TODO Auto-generated method stub
		permissionDao.deletePermissionById(permissionId);
	}

	@Override
	public void insertPermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.insertPermission(permission);
	}

	@Override
	public List<Permission> findAllPermission() {
		// TODO Auto-generated method stub
		return permissionDao.findAllPermission();
	}

}
