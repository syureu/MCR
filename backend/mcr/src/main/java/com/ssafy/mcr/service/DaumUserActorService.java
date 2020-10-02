package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumUserActor;
import com.ssafy.mcr.dto.Ranking;

public interface DaumUserActorService {
	public int addDaumUserActor(DaumUserActor daumUserActor);
	public void deletDaumUserActor(int userNo,int personId);
	public List<DaumUserActor> getDaumUserActorByUserNo(int userNo);
	public DaumUserActor checkLike(int userNo, int personId);
	public List<Ranking> likeRank10();
}
