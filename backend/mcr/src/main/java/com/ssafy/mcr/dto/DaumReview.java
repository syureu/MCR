package com.ssafy.mcr.dto;

public class DaumReview {
	private int movie_id;
	private String writer;
	private int rate;
	private String content;
	private String regtime;
	private int daum_review_no;
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
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
	public int getDaum_review_no() {
		return daum_review_no;
	}
	public void setDaum_review_no(int daum_review_no) {
		this.daum_review_no = daum_review_no;
	}
	@Override
	public String toString() {
		return "DaumReview [movie_id=" + movie_id + ", writer=" + writer + ", rate=" + rate + ", content=" + content
				+ ", regtime=" + regtime + ", daum_review_no=" + daum_review_no + "]";
	}
	
}
