package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumActorDao;
import com.ssafy.mcr.dao.DaumUserActorDao;
import com.ssafy.mcr.dto.DaumActor;
import com.ssafy.mcr.dto.DaumUserActor;
import com.ssafy.mcr.dto.Ranking;

@Service
public class DaumUserActorServiceImpl implements DaumUserActorService{

	@Autowired
	DaumUserActorDao daumUserActorDao;

	@Override
	public int addDaumUserActor(DaumUserActor daumUserActor) {
		return daumUserActorDao.insertDaumUserActor(daumUserActor);
	}

	@Override
	public void deletDaumUserActor(int userNo, int personId) {
		daumUserActorDao.deleteDaumUserActor(userNo, personId);
	}

	@Override
	public List<DaumUserActor> getDaumUserActorByUserNo(int userNo) {
		return daumUserActorDao.selectByUserNo(userNo);
	}

	@Override
	public DaumUserActor checkLike(int userNo, int personId) {
		return daumUserActorDao.checkLike(userNo, personId);
	}

	@Override
	public List<Ranking> likeRank10() {
		return daumUserActorDao.likeRank10();
	}

}
