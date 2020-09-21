package com.ssafy.mcr.dto;

public class DaumActor {
	private String actorName;
	private String actorNameEn;
	private int personId;
	private String gender;
	private String job;
	private String birth;
	private String imgUrl;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getActorNameEn() {
		return actorNameEn;
	}
	public void setActorNameEn(String actorNameEn) {
		this.actorNameEn = actorNameEn;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "DaumActor [actorName=" + actorName + ", actorNameEn=" + actorNameEn + ", personId=" + personId
				+ ", gender=" + gender + ", job=" + job + ", birth=" + birth + ", imgUrl=" + imgUrl + "]";
	}
	
}
