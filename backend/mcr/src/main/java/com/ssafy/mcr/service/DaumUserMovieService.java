package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumUserMovie;
import com.ssafy.mcr.dto.Ranking;

public interface DaumUserMovieService {
	public int addDaumUserMovie(DaumUserMovie daumUserMovie);
	public void deletDaumUserMovie(int userNo,int movieId);
	public List<DaumUserMovie> getDaumUserMovieByUserNo(int userNo);
	public DaumUserMovie checkLike(int userNo, int movieId);
	public List<Ranking> likeRank10();
}
