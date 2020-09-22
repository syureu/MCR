package com.ssafy.mcr.dto;

public class User {
<<<<<<< HEAD
	private int userNo;
	private String userid;
	private String pw;
	private String birthday;
	private boolean gender;
	private String nation;
	private String city;
	private String role;
	
	public boolean isGender() {
		return gender;
=======
	private String name;
	private String id;
	private String password;
	private String email;
	private int age;
	private boolean gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
>>>>>>> frontend-dev
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
<<<<<<< HEAD
		return "User [userNo=" + userNo + ", userid=" + userid + ", pw=" + pw + ", birthday=" + birthday + ", gender="
				+ gender + ", nation=" + nation + ", city=" + city + ", role=" + role + "]";
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
=======
		return "User [name=" + name + ", id=" + id + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", gender=" + gender + "]";
>>>>>>> frontend-dev
	}

	
}
