package com.ssafy.mcr.dto;

public class Paging {
	private String title;
	private int page;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "paging [title=" + title + ", page=" + page + "]";
	}
	public Paging(String title, int page) {
		super();
		this.title = title;
		this.page = page;
	}
}
