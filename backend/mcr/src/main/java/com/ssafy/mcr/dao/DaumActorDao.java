package com.ssafy.mcr.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumActor;

@Mapper
public interface DaumActorDao {
	public void insertDaumActor(DaumActor daumActor);
	public DaumActor selectDaumActorBypersonId(int personId);
}
