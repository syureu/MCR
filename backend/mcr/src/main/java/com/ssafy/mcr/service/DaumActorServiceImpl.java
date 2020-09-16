package com.ssafy.mcr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumActorDao;
import com.ssafy.mcr.dto.DaumActor;

@Service
public class DaumActorServiceImpl implements DaumActorService{

	@Autowired
	DaumActorDao daumActorDao;
	
	@Override
	public void addDaumActor(DaumActor daumActor) {
		daumActorDao.insertDaumActor(daumActor);
	}

	@Override
	public DaumActor getDaumActorBypersonId(int personId) {
		return daumActorDao.selectDaumActorBypersonId(personId);
	}

}
