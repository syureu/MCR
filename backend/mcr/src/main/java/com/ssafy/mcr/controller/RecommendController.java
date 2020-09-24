package com.ssafy.mcr.controller;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.service.RecommendService;
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

    @GetMapping("/simplegenre/{genre}")
    public ResponseEntity<RecommendListV1> readRecommend(@PathVariable String genre) {
        return new ResponseEntity<>(recommendService.selectGenreRecommend(genre), HttpStatus.OK);
    }
}
