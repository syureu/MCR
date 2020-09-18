package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumMoviePhotoDao;
import com.ssafy.mcr.dto.DaumMoviePhoto;

@Service
public class DaumMoviePhotoServiceImpl implements DaumMoviePhotoService{

	@Autowired
	DaumMoviePhotoDao daumMoviePhotoDao;
	

	@Override
	public void addDaumMoviePhoto(DaumMoviePhoto daumMoviePhoto) {
		daumMoviePhotoDao.insertDaumMoviePhoto(daumMoviePhoto);
	}

	@Override
	public List<DaumMoviePhoto> getDaumMoviePhotoBymovieId(int movieId) {
		return daumMoviePhotoDao.selectDaumMoviePhotoBymovieId(movieId);
	}


}
