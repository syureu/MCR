package com.ssafy.mcr.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.Actor;

@Mapper
public interface ActorDao {
	public Actor selectActorByCd(String actorCd);
}
