package com.ssafy.mcr.controller;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.exception.NothingToPrefException;
import com.ssafy.mcr.service.RecommendService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    RecommendService recommendService;

    public RecommendController(final RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/simplegenre/random/{userNo}")
    public ResponseEntity<RecommendListV1> simpleGenreRecommendByPrefer(@PathVariable Long userNo) {
        try {
            String genre = recommendService.getRandomGenreByUsersPrefer(userNo);
            return simpleGenreRecommendByGenre(genre);
        } catch (NothingToPrefException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/simplegenre/{genre}")
    public ResponseEntity<RecommendListV1> simpleGenreRecommendByGenre(@PathVariable String genre) {
        return new ResponseEntity<>(recommendService.selectGenreRecommend(genre), HttpStatus.OK);
    }
}
