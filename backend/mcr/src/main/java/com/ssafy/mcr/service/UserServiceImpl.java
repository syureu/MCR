package com.ssafy.mcr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.UserDao;
import com.ssafy.mcr.dto.User;


@Service
public class UserServiceImpl implements UserService {
	
	 
	@Autowired
	UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.insertUser(user);
	} 

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}
	@Override
	public void deleteUser(String userId, String pw) {
		userDao.deleteUser(userId,pw);
	}

	@Override
	public User getUserbyId(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public User getUserbyIdandPwd(String id, String password) {
		return userDao.selectUserByIdAndPwd(id, password);
	}

	@Override
	public User getUserbyNo(int userNo) {
		return userDao.selectUserByNo(userNo);
	}

	

}
