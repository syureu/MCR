package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumUserMovie;

@Mapper
public interface DaumUserMovieDao {
	public int insertDaumUserMovie(DaumUserMovie daumUserMovie);
	public int deleteDaumUserMovie(int userNo,int movieId);
	public List<DaumUserMovie> selectByUserNo(int userNo);
}
