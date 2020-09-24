package com.ssafy.mcr.dto;

import java.util.List;

public class MovieList {
	int totCnt;
	String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	List<MovieData> movieList;
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public List<MovieData> getMovieList() {
		return movieList;
	}
	public void setMovieList(List<MovieData> movieList) {
		this.movieList = movieList;
	}
	@Override
	public String toString() {
		return "MovieList [totCnt=" + totCnt + ", movieList=" + movieList + "]";
	}
	
}
