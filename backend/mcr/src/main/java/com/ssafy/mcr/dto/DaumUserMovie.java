package com.ssafy.mcr.dto;

public class DaumUserMovie {
	private int userNo;
	private int movieId;
	private int searchpoint;
	private int check;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getSearchpoint() {
		return searchpoint;
	}
	public void setSearchpoint(int searchpoint) {
		this.searchpoint = searchpoint;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "DaumUserMovie [userNo=" + userNo + ", movieId=" + movieId + ", searchpoint=" + searchpoint + ", check="
				+ check + "]";
	}
}
