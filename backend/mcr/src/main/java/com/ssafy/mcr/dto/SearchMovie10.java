package com.ssafy.mcr.dto;

import java.util.List;

public class SearchMovie10 {
	String title;
	List<DaumMovie> movies;
	public List<DaumMovie> getMovies() {
		return movies;
	}
	public void setMovies(List<DaumMovie> movies) {
		this.movies = movies;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SearchMovie10 [title=" + title + ", movies=" + movies + "]";
	}
}
