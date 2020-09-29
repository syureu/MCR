package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.Paging;

@Mapper
public interface DaumMovieDao {
	public void insertDaumMovie(DaumMovie daumMovie);
	public void updateDaumMovieRate(DaumMovie daumMovie);
	public void upScore(DaumMovie daumMovie);
	public List<DaumMovie> selectLimit10ByScore();
	public DaumMovie selectDaumMovieBymovieId(int movieId);
	public List<DaumMovie> selectByTitle(String title);
	public List<DaumMovie> selectLimit30(Paging paging);
}
