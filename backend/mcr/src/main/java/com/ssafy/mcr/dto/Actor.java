package com.ssafy.mcr.dto;

import java.sql.Date;

public class Actor {
	private String actorName;
	private String actorNameEn;
	private String actorCd;
	private String job;
	private Date birthday;
	private String imgUrl;
	private boolean gender;
	public String getActorNameEn() {
		return actorNameEn;
	}
	public void setActorNameEn(String actorNameEn) {
		this.actorNameEn = actorNameEn;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	public String getActorCd() {
		return actorCd;
	}
	public void setActorCd(String actorCd) {
		this.actorCd = actorCd;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Actor [actorName=" + actorName + ", actorNameEn=" + actorNameEn + ", actorCd=" + actorCd + ", job="
				+ job + ", birthday=" + birthday + ", imgUrl=" + imgUrl + ", gender=" + gender + "]";
	}
	
}

