package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.Paging;

public interface DaumMovieService {
	public void addDaumMovie(DaumMovie daumMovie);
	public void addScore(DaumMovie daumMovie);
	public DaumMovie getDaumMovieBymovieId(int movieId);
	public List<DaumMovie> getDaumMovieByTitle(String title);
	public List<DaumMovie> getLimit30(Paging paging);
	public List<DaumMovie> getLimit10ByScore();
}
