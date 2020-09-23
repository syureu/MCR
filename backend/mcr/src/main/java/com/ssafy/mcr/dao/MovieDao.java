package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.Movie;

@Mapper
public interface MovieDao {
	public void insertMovie(Movie movie);
	public void updateMovie(Movie movie);
	public void deleteMovie(String movieCd);
	public Movie selectMovieByMovieCd(String movieCd);
	public List<Movie> selectMovieByMovieNm(String movieNm);
}
