package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumReview;

@Mapper
public interface DaumReviewDao {
	public void insertDaumReview(DaumReview daumReview);
	public List<DaumReview> selectDaumPosReviewsByMovieId(int movieId);
	public List<DaumReview> selectDaumNagReviewsByMovieId(int movieId);
	public List<DaumReview> selectAllDaumReviewsByMovieId(int movieId);
}