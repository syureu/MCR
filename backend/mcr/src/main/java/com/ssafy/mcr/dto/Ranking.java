package com.ssafy.mcr.dto;

public class Ranking {
	int id;
	int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Ranking [id=" + id + ", count=" + count + "]";
	}
	
}
