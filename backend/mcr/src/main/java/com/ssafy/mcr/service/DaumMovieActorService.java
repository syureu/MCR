package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumMovieActor;

public interface DaumMovieActorService {
	public void addDaumMovieActor(DaumMovieActor daumMovie);
	public List<DaumMovieActor> getDaumMovieActorBymovieId(int movieId);
	public List<DaumMovieActor> getDaumMovieActorBypersonId(int personId);
}
