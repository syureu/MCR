package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.Paging;

public interface DaumMovieService {
	public void addDaumMovie(DaumMovie daumMovie);
	public void updateDaumMovieRate(DaumMovie daumMovie);
	public void addScore(DaumMovie daumMovie);
	public DaumMovie getDaumMovieBymovieId(int movieId);
	public int getDaumMovieIdByTitle(String title);
	public List<DaumMovie> getDaumMovieByTitle(String title);
	public List<DaumMovie> getLimit30(Paging paging);
	public List<DaumMovie> getLimit10ByScore(int page);
	public List<DaumMovie> getKoreaMovieByGenre(String genre);
	public List<DaumMovie> getForeignMovieByGenre(String genre);
}
