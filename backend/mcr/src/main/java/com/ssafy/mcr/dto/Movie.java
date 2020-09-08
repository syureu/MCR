package com.ssafy.mcr.dto;

public class Movie {
	private String movieCd;
	private String movieNm;
	private String movieNmEn;
	private String movieNmOg;
	private String year;
	private String genre;
	private String repNationNm;
	private String director;
	private int clickCount;
	
	public String getMovieNmEn() {
		return movieNmEn;
	}
	public void setMovieNmEn(String movieNmEn) {
		this.movieNmEn = movieNmEn;
	}
	public String getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getMovieNmOg() {
		return movieNmOg;
	}
	public void setMovieNmOg(String movieNmOg) {
		this.movieNmOg = movieNmOg;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRepNationNm() {
		return repNationNm;
	}
	public void setRepNationNm(String repNationNm) {
		this.repNationNm = repNationNm;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	@Override
	public String toString() {
		return "Movie [movieCd=" + movieCd + ", movieNm=" + movieNm + ", movieNmOg=" + movieNmOg + ", year=" + year
				+ ", genre=" + genre + ", repNationNm=" + repNationNm + ", director=" + director + ", clickCount="
				+ clickCount + "]";
	}
	
	
	
}
