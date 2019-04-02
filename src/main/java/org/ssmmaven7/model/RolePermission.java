package org.ssmmaven7.model;

import java.io.Serializable;

public class RolePermission implements Serializable{
	private Integer rolePermissionId ;
	private Integer permissionId;
	private Integer roleId;
	
	public RolePermission(){}
	/**
	 * 
	 * @param rolePermissionId   角色权限分配编号
	 * @param permissionId       权限编号
	 * @param roleId             角色编号
	 */
	public RolePermission(Integer rolePermissionId, Integer permissionId, Integer roleId){
		this.rolePermissionId = rolePermissionId;
		this.permissionId = permissionId;
		this.roleId = roleId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	
	
}
