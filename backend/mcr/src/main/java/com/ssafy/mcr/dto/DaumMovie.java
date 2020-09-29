package com.ssafy.mcr.dto;

public class DaumMovie {
	private String movieName;
	private String movieNameEn;
	private int movieId;
	private double rate;
	private String genre;
	private String movieOpeningDate;
	private String runningTime;
	private String overview;
	private String imgUrl;
	private String nation;
	private int score;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieNameEn() {
		return movieNameEn;
	}
	public void setMovieNameEn(String movieNameEn) {
		this.movieNameEn = movieNameEn;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getMovieOpeningDate() {
		return movieOpeningDate;
	}
	public void setMovieOpeningDate(String movieOpeningDate) {
		this.movieOpeningDate = movieOpeningDate;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	@Override
	public String toString() {
		return "DaumMovie [movieName=" + movieName + ", movieNameEn=" + movieNameEn + ", movieId=" + movieId + ", rate="
				+ rate + ", genre=" + genre + ", movieOpeningDate=" + movieOpeningDate + ", runningTime=" + runningTime
				+ ", overview=" + overview + ", imgUrl=" + imgUrl + ", nation=" + nation + ", score=" + score + "]";
	}
}
