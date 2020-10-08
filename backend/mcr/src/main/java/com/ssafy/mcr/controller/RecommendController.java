package com.ssafy.mcr.controller;

import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.exception.NothingToPrefException;
import com.ssafy.mcr.exception.UnknownEnvironmentException;
import com.ssafy.mcr.service.RecommendService;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    RecommendService recommendService;

    public RecommendController(final RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    /*
    @GetMapping("/")
    public ResponseEntity<RecommendListV1> readRecommend() {
        return new ResponseEntity<>(recommendService.selectRecommend(), HttpStatus.OK);
    }
     */

    @GetMapping("/{userNo}")
    public ResponseEntity<RecommendListV1> randomRecommend(@PathVariable Integer userNo) {
        // 완전 랜덤 추천
        // 영화를 랜덤하게 추천하는 것이 아닌
        // 추천 알고리즘 중에 랜덤적으로 선택하도록 하는 것
        int randomFlag = 0;
        // 어떤 추천을 적용할지 선택하는 부분

        switch (randomFlag) {
            case 0:
                // 사용자 요소 사용하지 않은 내부 점수 추천
                return simpleRecommend();
            case 1:
                // 사용자 요소 사용한 장르 기반 내부 점수 추천
                return simpleRecommendByUserNo(userNo);
            default:
                System.out.println("랜덤 값 결정에 문제가 있습니다.");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/simple")
    public ResponseEntity<RecommendListV1> simpleRecommend() {
        try {
            return new ResponseEntity<>(recommendService.simpleRecommend(), HttpStatus.OK);
        } catch (UnknownEnvironmentException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/simple/{genre}")
    public ResponseEntity<RecommendListV1> simpleRecommendByGenre(@PathVariable String genre) {
        try {
            return new ResponseEntity<>(recommendService.simpleRecommendByGenre(genre), HttpStatus.OK);
        } catch (UnknownEnvironmentException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/simple/random/{userNo}")
    public ResponseEntity<RecommendListV1> simpleRecommendByUserNo(@PathVariable Integer userNo) {
        String usersPreferGenre;
        try {
            usersPreferGenre = recommendService.getUsersRandomGenreByUsersPrefer(userNo);
            // if pass, 선호하는 영화가 하나라도 있음
            // 그러므로 선호하는 영화의 장르를 기반으로 한 추천
            return simpleRecommendByGenre(usersPreferGenre);
        } catch (NothingToPrefException e) {
            // 선호하는 영화가 없을 경우
            // 사용자의 요소와 관계없이 영화 추천
            return simpleRecommend();
        }
    }

    @GetMapping("/similarity/random/{userNo}")
    public ResponseEntity<RecommendListV1> similarityRecommendByUserNo(@PathVariable Integer userNo) {
        DaumMovie usersPreferMovie;
        try {
            usersPreferMovie = recommendService.getUsersRandomMovieByUsersPrefer(userNo);
            // if pass, 선호하는 영화가 하나라도 있음
            // 이 영화가 유사도 추천이 가능한 영화 인가?
            boolean isCanRecommendBySimilarity = recommendService.canRecommendBySimilarity(usersPreferMovie.getMovieId());
            if(isCanRecommendBySimilarity) {
                return new ResponseEntity<>(recommendService.recommendBySimilarity(usersPreferMovie), HttpStatus.OK);
            } else {
                // 추천 불가능하면 No Content
                System.out.println(userNo + "번 회원의 " + usersPreferMovie.getMovieId() + "는 추천이 불가능 합니다.");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (NothingToPrefException e) {
            // 선호하는 영화가 없을 경우
            // 클라이언트에서 추천할게 없다고 반응할 수 있도록 No Content 처리
            System.out.println(userNo + "는 선호하는 영화가 없어 보입니다.");
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (UnknownEnvironmentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
