package org.ssmmaven7.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssmmaven7.model.Permission;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IRolePermissionService;
import org.ssmmaven7.service.IUserService;
import org.ssmmaven7.service.impl.UserService;

import util.Consol;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;
	@Autowired
	private IRolePermissionService rolePermissionService;
	
	/**
	 * 授权查询回调函数，实现授权     权限鉴定是发现缓存中没有用户的权限信息时调用
	 * 
	 * roleId = 1    客服
	 * roleId = 2    客服组长 
	 * roleId = 3    导购员
	 * roleId = 4    导购组长
	 * roleId = 5    店长
	 * roleId = 6    店长助理
	 * roleId = 7    货管员
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
//																		String userName = (String) principalCollection.fromRealm(getName())
//																							.iterator().next();
//																		User user = userService.findByUserName(userName);
		String userName = (String) principalCollection.fromRealm(getName())
				.iterator().next();
		User user = userService.findByUserName(userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////																		在info中添加角色和角色对应的权限     一个权限高的用户相当于拥有多个角色，也对应多个权限				
		int roleId = user.getRoleId();
		info.addRole(roleId+"");
//		再添加role对应的权限permission
		Set<String> permissionSet = rolePermissionService.findPermissionCodeByRoleId(roleId);
		info.setStringPermissions(permissionSet);
		for(String str : permissionSet){
			Consol.log(str);
		}
		return info;
	}
	
	/**
	 * 认证回调函数，    实现登录认证，登录时调用  
	 * 认证方法     身份认证  用于 登录  从token获取登陆的用户和密码， ，token在filter中登录时生成生成   校验用户输入的信息
     * 验证账号密码    UsernamePasswordToken封装了用户名和密码，也可以自定义一个继承UsernamePasswordToken
     * 注：token中封装的是用户输入的信息，用它去比对数据库中的信息，看是否得到成功匹配的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken npToken = (UsernamePasswordToken) token;
		String userName = npToken.getUsername();
		User user = null ;
		user = userService.findByUserName(userName);
		if(user == null){
			//在loginController中调用验证时捕获异常，如果有异常则登录出错
			throw new UnknownAccountException("用户名或密码错误");
		}
		/* 三个参数      封装初步认证信息  ,第二个参数是从数据库中获取的password不是用户输入的password*/
		Object principal = userName;
		Object credentials = user.getUserPwd();
		String realName = getName();
		//使用useName生成公盐
		ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal
				,credentials,realName);
		//最后的对比交给安全管理器      SimpleAuthenticationInfo在权限管理Authorization中会用到
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal
//				,credentials,credentialsSalt,realName);		
		return info;
	}
	 
}
