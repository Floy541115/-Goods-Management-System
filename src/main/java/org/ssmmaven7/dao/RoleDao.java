package org.ssmmaven7.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Role;

public interface RoleDao {
	
	public List<Role> findAllRole();
	public void deleteByRoleId(@Param("roleId")int roleId);
	public Set<Role> findAllRoleAndPms();//find all roles and their permissions 
	public void insertRole(Role role);
}
