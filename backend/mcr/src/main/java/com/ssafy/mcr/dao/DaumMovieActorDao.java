package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumMovieActor;

@Mapper
public interface DaumMovieActorDao {
	public void insertDaumMovieActor(DaumMovieActor daumMovieActor);
	public List<DaumMovieActor> selectDaumMovieActorBypersonId(int personId);
	public List<DaumMovieActor> selectDaumMovieActorBymovieId(int movieId);
}
