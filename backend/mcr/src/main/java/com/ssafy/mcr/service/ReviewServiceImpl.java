package com.ssafy.mcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mcr.dao.ReviewDao;
import com.ssafy.mcr.dto.Review;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public void addReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	public void modifyReview(Review review) {
		reviewDao.updateReview(review);
	}

	@Override
	public void deleteReview(int reviewNo) {
		reviewDao.deleteReview(reviewNo);
	}

	@Override
	public Review getReviewbyNo(int reviewNo) {
		return reviewDao.selectReviewByNo(reviewNo);
	}

	@Override
	public List<Review> getReviewsByMovieCd(String movieCd) {
		return reviewDao.selectReviewsByMovieCd(movieCd);
	}
	
	 

}
