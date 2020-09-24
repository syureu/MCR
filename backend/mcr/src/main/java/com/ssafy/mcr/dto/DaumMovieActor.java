package com.ssafy.mcr.dto;

public class DaumMovieActor {
	private int movieId;
	private int personId;
	private String casting;
	private int check;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getCasting() {
		return casting;
	}
	public void setCasting(String casting) {
		this.casting = casting;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "DaumMovieActor [movieId=" + movieId + ", personId=" + personId + ", casting=" + casting + ", check="
				+ check + "]";
	}
	
}
