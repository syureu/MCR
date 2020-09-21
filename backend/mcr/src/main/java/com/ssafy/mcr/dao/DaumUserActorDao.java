package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumUserActor;

@Mapper
public interface DaumUserActorDao {
	public int insertDaumUserActor(DaumUserActor daumUserActor);
	public int deleteDaumUserActor(int userNo,int personId);
	public List<DaumUserActor> selectByUserNo(int userNo);
}
