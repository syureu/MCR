package com.ssafy.mcr.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.User;


@Mapper
public interface UserDao {
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(String userid);
	public User selectUserById(String userid);
	public User selectUserByIdAndPwd(String userid,String pw);
}
