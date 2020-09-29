package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumReview;

@Mapper
public interface DaumReviewDao {
	public void insertDaumReview(DaumReview daumReview);
	public void updateDaumReview(DaumReview daumReview);
	public void deleteDaumReview(int movieId, int userNo);
	public List<DaumReview> selectDaumPosReviewsByMovieId(int movieId);
	public List<DaumReview> selectDaumNagReviewsByMovieId(int movieId);
	public List<DaumReview> selectAllDaumReviewsByMovieId(int movieId);
	public DaumReview selectDaumReviewByNo(int movieId, int userNo);
	public void insertCount(int movieId);
}
