package com.ssafy.mcr.service;

import java.util.List;

import com.ssafy.mcr.dto.DaumUserActor;

public interface DaumUserActorService {
	public int addDaumUserActor(DaumUserActor daumUserActor);
	public void deletDaumUserActor(int userNo,int personId);
	public List<DaumUserActor> getDaumUserActorByUserNo(int userNo);
}
