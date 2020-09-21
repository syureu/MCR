package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumUserMovieDao;
import com.ssafy.mcr.dto.DaumUserMovie;

@Service
public class DaumUserMovieServiceImpl implements DaumUserMovieService{

	@Autowired
	DaumUserMovieDao daumUserMovieDao;

	@Override
	public int addDaumUserMovie(DaumUserMovie daumUserMovie) {
		return daumUserMovieDao.insertDaumUserMovie(daumUserMovie);
	}

	@Override
	public void deletDaumUserMovie(int userNo, int personId) {
		daumUserMovieDao.deleteDaumUserMovie(userNo, personId);
	}

	@Override
	public List<DaumUserMovie> getDaumUserMovieByUserNo(int userNo) {
		return daumUserMovieDao.selectByUserNo(userNo);
	}

}
