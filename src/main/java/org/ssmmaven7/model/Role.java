package org.ssmmaven7.model;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{
	
	private Integer roleId;
	private String roleName;
	private Set<Permission> permissions;

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    /**
     * 
     * @param roleId    角色编号
     * @param roleName   角色名
     * @param permissions  角色权限集合
     */
	public Role(Integer roleId, String roleName, Set<Permission> permissions){
		this.roleId = roleId;
		this.roleName = roleName;
		this.permissions = permissions;
	}
	
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public Role(){}
}
