package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumUserMovieDao;
import com.ssafy.mcr.dto.DaumUserActor;
import com.ssafy.mcr.dto.DaumUserMovie;
import com.ssafy.mcr.dto.Ranking;

@Service
public class DaumUserMovieServiceImpl implements DaumUserMovieService{

	@Autowired
	DaumUserMovieDao daumUserMovieDao;

	@Override
	public int addDaumUserMovie(DaumUserMovie daumUserMovie) {
		return daumUserMovieDao.insertDaumUserMovie(daumUserMovie);
	}

	@Override
	public void deletDaumUserMovie(int userNo, int movieId) {
		daumUserMovieDao.deleteDaumUserMovie(userNo, movieId);
	}

	@Override
	public List<DaumUserMovie> getDaumUserMovieByUserNo(int userNo) {
		return daumUserMovieDao.selectByUserNo(userNo);
	}

	@Override
	public DaumUserMovie checkLike(int userNo, int movieId) {
		return daumUserMovieDao.checkLike(userNo, movieId);
	}

	@Override
	public List<Ranking> likeRank10() {
		return daumUserMovieDao.likeRank10();
	}

}
