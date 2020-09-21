package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumUserMovie;

public interface DaumUserMovieService {
	public int addDaumUserMovie(DaumUserMovie daumUserMovie);
	public void deletDaumUserMovie(int userNo,int personId);
	public List<DaumUserMovie> getDaumUserMovieByUserNo(int userNo);
}
