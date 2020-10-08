package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumUserActor;
import com.ssafy.mcr.dto.Ranking;

@Mapper
public interface DaumUserActorDao {
	public int insertDaumUserActor(DaumUserActor daumUserActor);
	public int deleteDaumUserActor(int userNo,int personId);
	public List<DaumUserActor> selectByUserNo(int userNo);
	public DaumUserActor checkLike(int userNo, int personId);
	public List<Ranking> likeRank10();
}
