package org.ssmmaven7.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ssmmaven7.dao.RoleDao;
import org.ssmmaven7.dao.RolePermissionDao;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.service.IRedisService;
import org.ssmmaven7.service.IRoleService;

import util.JsonUtil;
@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public String findAllRole() {    //redisCacheAspect
		// TODO Auto-generated method stub
		List<Role> list = roleDao.findAllRole();
		String jsonString = JsonUtil.objectToJsonString(list);
		return jsonString ;
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Object deleteByRoleId(int roleId) {     //直接配置Transactional
		// TODO Auto-generated method stub
		roleDao.deleteByRoleId(roleId);
		rolePermissionDao.deleteRolePermissionByRoleId(roleId);
		return null;
	}

	@Override
	public Set<Role> findAllRoleAndPms() {
		// TODO Auto-generated method stub
		return roleDao.findAllRoleAndPms();
	}

	@Override
	public void insertRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.insertRole(role);
	}
	
	
}
