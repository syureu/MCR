package com.ssafy.mcr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.mcr.dto.Movie;

@Service
public interface MovieService {
	public void addMovie(Movie movie);
	public void modifyMovie(Movie movie);
	public void deleteMovie(String movieCd);
	public Movie getMovieByMovieCd(String movieCd);
	public List<Movie> getMovieBymovieNm(String movieNm);
}
