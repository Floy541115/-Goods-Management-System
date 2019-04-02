package org.ssmmaven7.service;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Role;

public interface IRoleService {
	public String findAllRole();
	public Object deleteByRoleId(@Param("roleId")int roleId);
	public Set<Role> findAllRoleAndPms();
	public void insertRole(Role role);
}
