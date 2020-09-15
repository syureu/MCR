package com.ssafy.mcr.service;

import com.ssafy.mcr.dto.DaumActor;

public interface DaumActorService {
	public void addDaumActor(DaumActor daumActor);
	public DaumActor getDaumActorBypersonId(int personId);
}
