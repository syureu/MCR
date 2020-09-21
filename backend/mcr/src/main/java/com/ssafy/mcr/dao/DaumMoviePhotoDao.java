package com.ssafy.mcr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mcr.dto.DaumMoviePhoto;

@Mapper
public interface DaumMoviePhotoDao {
	public void insertDaumMoviePhoto(DaumMoviePhoto daumMoviePhoto);
	public List<DaumMoviePhoto> selectDaumMoviePhotoBymovieId(int movieId);
}
