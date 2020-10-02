package com.ssafy.mcr.dao;

import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.RecommendV1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendDao {
    // List<RecommendV1> selectRecommendV1();
    DaumMovie selectUsersRandomPreferMovie(Integer userNo);
}
