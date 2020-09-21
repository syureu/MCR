package com.ssafy.mcr.service;

import com.ssafy.mcr.dto.DaumMovie;

public interface DaumMovieService {
	public void addDaumMovie(DaumMovie daumMovie);
	public DaumMovie getDaumMovieBymovieId(int movieId);
}
