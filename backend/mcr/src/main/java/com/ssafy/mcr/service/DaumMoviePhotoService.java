package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumMoviePhoto;

public interface DaumMoviePhotoService {
	public void addDaumMoviePhoto(DaumMoviePhoto daumMoviePhoto);
	public List<DaumMoviePhoto> getDaumMoviePhotoBymovieId(int movieId);
}
