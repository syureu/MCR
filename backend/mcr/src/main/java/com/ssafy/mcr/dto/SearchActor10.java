package com.ssafy.mcr.dto;

import java.util.List;

public class SearchActor10 {
	String title;
	List<DaumActor> actors;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<DaumActor> getActors() {
		return actors;
	}
	public void setActors(List<DaumActor> actors) {
		this.actors = actors;
	}
	@Override
	public String toString() {
		return "SearchActor10 [title=" + title + ", actors=" + actors + "]";
	}
	
}
