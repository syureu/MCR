package com.ssafy.mcr.service;

import com.ssafy.mcr.dao.RecommendDao;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.dto.RecommendV1;
import com.ssafy.mcr.recommend.Recommend;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendService {

    RecommendDao recommendDao;
    Recommend recommend;
    // List<String> recommendMent;

    public RecommendService(final RecommendDao recommendDao, final Recommend recommend) {
        this.recommendDao = recommendDao;
        this.recommend = recommend;

        /*
        recommendMent = new ArrayList<>();
        recommendMent.add("Science Fiction 장르의 인기");
        recommendMent.add("Comedy 장르의 인기");
        recommendMent.add("Drama 장르의 인기");
        recommendMent.add("Crime 장르의 인기");
        recommendMent.add("Action 장르의 인기");
        recommendMent.add("Adventure 장르의 인기");
        recommendMent.add("War 장르의 인기");
        recommendMent.add("Horror 장르의 인기");
        recommendMent.add("Thriller 장르의 인기");
        recommendMent.add("Fantasy 장르의 인기");
        recommendMent.add("Horror 장르의 인기");
        recommendMent.add("Documentary 장르의 인기");
         */
    }

    /*
    public RecommendListV1 selectRecommend() {
        recommend.simpleRecommend("");
        List<RecommendV1> list = recommendDao.selectRecommendV1();
        list.forEach(v -> {
            if (v.getPosterPath() != null)
                v.setPosterPath("https://image.tmdb.org/t/p/original" + v.getPosterPath());
        });
        return new RecommendListV1(recommendMent.get((int) (Math.random() * recommendMent.size())), list);
    }
     */

    public RecommendListV1 simpleRecommendByGenre(String genre) throws IOException {
        return recommend.simpleRecommendByGenre(genre);
    }

    public String getUsersRandomGenreByUsersPrefer(Integer userNo) {
        DaumMovie dm = recommendDao.selectUsersRandomPreferMovie(userNo);
        String[] genreList = dm.getGenre().split("/");
        return genreList[(int)(Math.random() * genreList.length)];
    }
}
