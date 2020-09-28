package com.ssafy.mcr.controller;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.service.RecommendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = { "*" })
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

    @GetMapping("/simple/{genre}")
    public ResponseEntity<RecommendListV1> simpleRecommendByGenre(@PathVariable String genre) {
        try {
            return new ResponseEntity<>(recommendService.simpleRecommendByGenre(genre), HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/simple/random/{userNo}")
    public ResponseEntity<RecommendListV1> simpleRecommendByUserNo(@PathVariable Integer userNo) {
        String usersPreferGenre = recommendService.getUsersRandomGenreByUsersPrefer(userNo);
        try {
            return new ResponseEntity<>(recommendService.simpleRecommendByGenre(usersPreferGenre), HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
