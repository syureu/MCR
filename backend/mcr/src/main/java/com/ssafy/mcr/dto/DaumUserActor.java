package com.ssafy.mcr.dto;

public class DaumUserActor {
	private int userNo;
	private int personId;
	private int searchpoint;
	private int check;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
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
		return "DaumUserActor [userNo=" + userNo + ", personId=" + personId + ", searchpoint=" + searchpoint
				+ ", check=" + check + "]";
	}
	
}
