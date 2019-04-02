package org.ssmmaven7.model;

import java.io.Serializable;

/**
 *    Permission  权限表        permissionId  permissionDetail 
 * 	  Role   角色     roleId  roleName
 *    RolePermission  角色权限表     roleId   permissionId   （匹配角色对应的权限）
 *    
 *    RolePermission指定一个角色对应的权限
 *    
 *    user -> role -> permission
 *    
 *    因为role--permission是多对多关系，所以要建一个中间连接表格
 *    
 *    删除role时应该同时删除role对应的rolePermission，而不是删除permission，因为role与permission是多对多的，这个permission可能要对应其他的role
 */

public class Permission implements Serializable{
	private Integer permissionId;
	private String url;
	private String description;
	private String resourceCode;
	
	public Permission(){}
	/**
	 * 
	 * @param permissionId    权限Id
	 * @param url			  
	 * @param description     权限描述
	 * @param resourceCode    权限编码
	 */
	public Permission(Integer permissionId, String url, String description,String resourceCode){
		this.permissionId = permissionId;
		this.url = url;
		this.description = description;
		this.resourceCode = resourceCode;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	
}
