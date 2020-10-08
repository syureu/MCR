package com.ssafy.mcr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.DaumReviewDao;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumReview;

@Service
public class DaumReviewServiceImpl implements DaumReviewService{

	@Autowired
	DaumReviewDao daumReviewDao;

	@Override
	public void addDaumReview(DaumReview daumReview) {
		daumReviewDao.insertDaumReview(daumReview);
	}

	@Override
	public List<DaumReview> getDaumPosReviewsByMovieId(int movieId) {
		return daumReviewDao.selectDaumPosReviewsByMovieId(movieId);
	}

	@Override
	public List<DaumReview> getDaumNagReviewsByMovieId(int movieId) {
		return daumReviewDao.selectDaumNagReviewsByMovieId(movieId);
	}
	
	@Override
	public List<DaumReview> getAllDaumReviewsByMovieId(int movieId) {
		return daumReviewDao.selectAllDaumReviewsByMovieId(movieId);
	}

	@Override
	public DaumReview getDaumReviewByNo(int movieId, int userNo) {
		return daumReviewDao.selectDaumReviewByNo(movieId, userNo);
	}

	@Override
	public void insertCount(int movieId) {
		daumReviewDao.insertCount(movieId);
	}

	@Override
	public void updateDaumReview(DaumReview daumReview) {
		daumReviewDao.updateDaumReview(daumReview);
	}

	@Override
	public void deleteDaumReview(int movieId, int userNo) {
		daumReviewDao.deleteDaumReview(movieId, userNo);
	}


}
