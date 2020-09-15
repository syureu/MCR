package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumReview;

@Mapper
public interface DaumReviewDao {
	public void insertDaumReview(DaumReview daumReview);
	public List<DaumReview> selectDaumReviewsByMovieId(int movieId);
}
