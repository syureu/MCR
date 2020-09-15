package com.ssafy.mcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumReviewDao;
import com.ssafy.mcr.dto.DaumReview;

@Service
public class DaumActorServiceImpl implements DaumReviewService{

	@Autowired
	DaumReviewDao daumReviewDao;
	
	@Override
	public void addDaumReview(DaumReview daumReview) {
		daumReviewDao.insertDaumReview(daumReview);
	}

	@Override
	public List<DaumReview> getDaumReviewsByMovieId(int movieId) {
		return daumReviewDao.selectDaumReviewsByMovieId(movieId);
	}

}
