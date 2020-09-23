package com.ssafy.mcr.dto;

public class Directors {
	private String peopleNm;

	@Override
	public String toString() {
		return "Directors [peopleNm=" + peopleNm + "]";
	}

	public String getPeopleNm() {
		return peopleNm;
	}

	public void setPeopleNm(String peopleNm) {
		this.peopleNm = peopleNm;
	}
}
