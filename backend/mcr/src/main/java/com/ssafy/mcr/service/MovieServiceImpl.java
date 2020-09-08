package com.ssafy.mcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.MovieDao;
import com.ssafy.mcr.dao.UserDao;
import com.ssafy.mcr.dto.Movie;
import com.ssafy.mcr.dto.User;


@Service
public class MovieServiceImpl implements MovieService {
	
	 
	@Autowired
	MovieDao movieDao;

	@Override
	public void addMovie(Movie movie) {
		movieDao.insertMovie(movie);
	}

	@Override
	public void modifyMovie(Movie movie) {
		movieDao.updateMovie(movie);
	}

	@Override
	public void deleteMovie(String movieCd) {
		movieDao.deleteMovie(movieCd);
	}

	@Override
	public Movie getMovieByMovieCd(String movieCd) {
		return movieDao.selectMovieByMovieCd(movieCd);
	}

	@Override
	public List<Movie> getMovieBymovieNm(String movieNm) {
		return movieDao.selectMovieByMovieNm(movieNm);
	}


	

}
