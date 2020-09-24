package com.ssafy.mcr.service;

import com.ssafy.mcr.dao.RecommendDao;
import com.ssafy.mcr.dto.Movie;
import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.dto.RecommendV1;
import com.ssafy.mcr.exception.NothingToPrefException;
import com.ssafy.mcr.recommend.Recommend;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendService {

    RecommendDao recommendDao;
    Recommend recommend;
    List<String> recommendMent;

    public RecommendService(final RecommendDao recommendDao, final Recommend recommend) {
        this.recommendDao = recommendDao;
        this.recommend = recommend;

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
    }

    public RecommendListV1 selectRecommend() {
        recommend.simpleGenreRecommend("코미디");
        List<RecommendV1> list = recommendDao.selectRecommendV1();
        list.forEach(v -> {
            if (v.getPosterPath() != null)
                v.setPosterPath("https://image.tmdb.org/t/p/original" + v.getPosterPath());
        });
        return new RecommendListV1(recommendMent.get((int) (Math.random() * recommendMent.size())), list);
    }

    public RecommendListV1 selectGenreRecommend(String genre) {
        return recommend.simpleGenreRecommend(genre);
    }

    public String getRandomGenreByUsersPrefer(Long userNo) throws NothingToPrefException {
        Movie movie = getRandomMovieByUsersPrefer(userNo);
        String[] genreList = movie.getGenre().split(",");
        return genreList[(int) Math.random() * genreList.length];
    }

    public Movie getRandomMovieByUsersPrefer(Long userNo) throws NothingToPrefException {
        Movie movie = recommendDao.selectRandomMovieByUsersPrefer(userNo);
        if (movie == null) {
            throw new NothingToPrefException();
        }
        return movie;
    }
}
