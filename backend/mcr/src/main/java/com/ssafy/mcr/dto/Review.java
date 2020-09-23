package com.ssafy.mcr.dto;

import java.sql.Date;

public class Review {
	private int reviewNo;
	private float rate;
	private int userNo;
	private String movieCd;
	private String content;
	private Date regtime;
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", rate=" + rate + ", userNo=" + userNo + ", movieCd=" + movieCd
				+ ", content=" + content + ", regtime=" + regtime + "]";
	}
}
