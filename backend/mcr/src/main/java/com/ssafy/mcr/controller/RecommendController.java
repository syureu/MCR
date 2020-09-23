package com.ssafy.mcr.controller;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.service.RecommendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    RecommendService recommendService;

    public RecommendController(final RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/")
    public ResponseEntity<RecommendListV1> readRecommend() {
        return new ResponseEntity<>(recommendService.selectRecommend(), HttpStatus.OK);
    }
}
