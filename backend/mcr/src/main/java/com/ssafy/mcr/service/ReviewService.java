package com.ssafy.mcr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.mcr.dto.Review;

@Service
public interface ReviewService {

	public void addReview(Review review);
	public void modifyReview(Review review);
	public void deleteReview(int reviewNo);
	public Review getReviewbyNo(int reviewNo);
	public List<Review> getReviewsByMovieCd(String movieCd);
}
