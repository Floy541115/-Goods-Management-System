package org.ssmmaven7.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.User;


public interface IUserService {
	public User findByUserId(int userId);
	public User findByPwdNameRoleId(String userPwd, String userName, int roleId);
	public User findByUserName(String userName);
	public Set<User> findUserByRoleId(int roleId);
	public List<User> findAllUser();
	public int addUser(User user);
	public void deleteUser(int userId);
	public void updateUser(String userName, String phone, String email, String address, int userId);
}
