package com.ssafy.mcr.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumReview;

public interface DaumReviewService {
	public void addDaumReview(DaumReview daumReview);
	public List<DaumReview> getDaumReviewsByMovieId(int movieId);
}
