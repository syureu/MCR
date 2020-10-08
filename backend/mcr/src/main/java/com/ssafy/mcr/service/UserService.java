package com.ssafy.mcr.service;

import org.springframework.stereotype.Service;

import com.ssafy.mcr.dto.User;


@Service
public interface UserService {

	public void addUser(User user);
	public void modifyUser(User user);
	public void deleteUser(String userId, String pw);
	public User getUserbyId(String id);
	public User getUserbyNo(int userNo);
	public User getUserbyIdandPwd(String id,String password);
}
