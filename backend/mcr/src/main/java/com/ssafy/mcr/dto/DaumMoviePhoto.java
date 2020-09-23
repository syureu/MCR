package com.ssafy.mcr.dto;

public class DaumMoviePhoto {
	private int photoNo;
	private String photoUrl;
	private int movieId;
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "DaumMoviePhoto [photoNo=" + photoNo + ", photoUrl=" + photoUrl + ", movieId=" + movieId + "]";
	}
}
