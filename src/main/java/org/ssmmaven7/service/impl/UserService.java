package org.ssmmaven7.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.dao.UserDao;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByPwdNameRoleId(String userPwd, String userName, int roleId) {
		// TODO Auto-generated method stub
		try {
			User user = userDao.findByPwdNameRoleId(userPwd, userName, roleId);
			if(user == null){
				return null;
			}else {
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		try {
			userDao.addUser(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userId);
	}

	@Override
	public void updateUser(String userName, String phone, String email,
			String address, int userId) {
		// TODO Auto-generated method stub
		userDao.updateUser(userName, phone, email, address, userId);
	}

	@Override
	public User findByUserId(int userId) {
		// TODO Auto-generated method stub
		return userDao.findByUserId(userId);
	}

	@Override
	public Set<User> findUserByRoleId(int roleId) {
		// TODO Auto-generated method stub
		Set<User> set = new HashSet<>();
		set = userDao.findUserByRoleId(roleId);
		return set;
	}

}
