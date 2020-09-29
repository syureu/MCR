package com.ssafy.mcr.dto;

public class DaumReview {
	private int movieId;
	private String writer;
	private int rate;
	private String content;
	private String regtime;
	private int daumReviewNo;
	private int count;
	private int userNo;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getDaumReviewNo() {
		return daumReviewNo;
	}
	public void setDaumReviewNo(int daumReviewNo) {
		this.daumReviewNo = daumReviewNo;
	}
	@Override
	public String toString() {
		return "DaumReview [movieId=" + movieId + ", writer=" + writer + ", rate=" + rate + ", content=" + content
				+ ", regtime=" + regtime + ", daumReviewNo=" + daumReviewNo + ", count=" + count + "]";
	}
}
