package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.Review;

@Mapper
public interface ReviewDao {
	public void insertReview(Review review);
	public void updateReview(Review review);
	public void deleteReview(int reviewNo);
	public Review selectReviewByNo(int reviewNo);
	public List<Review> selectReviewsByMovieCd(String movieCd);
}
