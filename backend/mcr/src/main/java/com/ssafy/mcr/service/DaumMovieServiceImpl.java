package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumMovieDao;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.Paging;

@Service
public class DaumMovieServiceImpl implements DaumMovieService{

	@Autowired
	DaumMovieDao daumMovieDao;
	
	@Override
	public void addDaumMovie(DaumMovie daumMovie) {
		daumMovieDao.insertDaumMovie(daumMovie);
	}

	@Override
	public DaumMovie getDaumMovieBymovieId(int movieId) {
		return daumMovieDao.selectDaumMovieBymovieId(movieId);
	}

	@Override
	public List<DaumMovie> getDaumMovieByTitle(String title) {
		return daumMovieDao.selectByTitle(title);
	}

	@Override
	public List<DaumMovie> getLimit30(Paging paging) {
		return daumMovieDao.selectLimit30(paging);
	}

	@Override
	public void addScore(DaumMovie daumMovie) {
		daumMovieDao.upScore(daumMovie);
	}

	@Override
	public List<DaumMovie> getLimit10ByScore(int page) {
		return daumMovieDao.selectLimit10ByScore(page);
	}

	@Override
	public void updateDaumMovieRate(DaumMovie daumMovie) {
		daumMovieDao.updateDaumMovieRate(daumMovie);
	}

	@Override
	public List<DaumMovie> getKoreaMovieByGenre(String genre) {
		return daumMovieDao.selectKoreaMovieByGenre(genre);
	}

	@Override
	public List<DaumMovie> getForeignMovieByGenre(String genre) {
		return daumMovieDao.selectForeignMovieByGenre(genre);
	}

	@Override
	public int getDaumMovieIdByTitle(String title) {
		return daumMovieDao.selectDaumMovieIdByTitle(title);
	}

}
