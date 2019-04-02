package org.ssmmaven7.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.User;

public interface UserDao {
	//必须加@Param("x"),否则在SQL语句无法识别变量  
	public User findByUserId(@Param("userId")int userId);
	public User findByPwdNameRoleId(@Param("userPwd")String userPwd, @Param("userName")String userName, @Param("roleId")int roleId);
	public User findByUserName(@Param("userName")String userName);
	public Set<User> findUserByRoleId(@Param("roleId")int roleId);
	public List<User> findAllUser();  
	//返回的结果List<Object>中的Object即resultMap中的所有<result>集，Object中的元素即对应<result>中的column字段
	public void addUser(User user); //这里不需要有@Param("user")，因为最终要的参数只是user中的部分字段，而不是user对象，mapping中的参数是要插入表格的数据字段，不是整个user，如果用@Param("user")则要求mapping中的参数就是user
	public void updateUser(@Param("userName")String userName, @Param("phone")String phone, @Param("email")String email,
			@Param("address")String address, @Param("userId")int userId);
	public void deleteUser(@Param("userId")int userId);
	
} 
