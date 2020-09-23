package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumMovieActorDao;
import com.ssafy.mcr.dto.DaumMovieActor;

@Service
public class DaumMovieActorServiceImpl implements DaumMovieActorService{

	@Autowired
	DaumMovieActorDao daumMovieActorDao;
	

	@Override
	public void addDaumMovieActor(DaumMovieActor daumMovieActor) {
		daumMovieActorDao.insertDaumMovieActor(daumMovieActor);
	}

	@Override
	public List<DaumMovieActor> getDaumMovieActorBymovieId(int movieId) {
		return daumMovieActorDao.selectDaumMovieActorBymovieId(movieId);
	}

	@Override
	public List<DaumMovieActor> getDaumMovieActorBypersonId(int personId) {
		return daumMovieActorDao.selectDaumMovieActorBypersonId(personId);
	}

}
