package com.ssafy.mcr.controller;

import java.util.List;

//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mcr.dto.BasicResponse;
import com.ssafy.mcr.dto.Movie;
import com.ssafy.mcr.dto.Review;
import com.ssafy.mcr.service.MovieService;
import com.ssafy.mcr.service.ReviewService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/review")
public class ReviewController {


	@Autowired
	ReviewService reviewService;

	@ApiOperation(value="리뷰를 입력받아 데이터베이스에 저장합니다.")
	@PostMapping()
	public Object registMovie(@RequestBody Review review) {
		System.out.println("리뷰 입력 진입");
		System.out.println(review.toString());
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			reviewService.addReview(review);
			result.status = true;
			result.data = "리뷰가 데이터베이스에 추가되었습니다.";
			result.object = review; 
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="리뷰의 고유번호(reviewNo)로 해당 리뷰를 데이터 베이스에서 삭제합니다.")
	@DeleteMapping()
	public Object deleteMovie(@RequestParam int reviewNo) {
		ResponseEntity response = null;
		System.out.println("리뷰 삭제 진입");
		final BasicResponse result = new BasicResponse();
		try {
			Review review = reviewService.getReviewbyNo(reviewNo);
			reviewService.deleteReview(reviewNo);
			result.status = true;
			result.data = review.getReviewNo() + "번 리뷰가  데이터베이스에서 삭제되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="리뷰의 고유번호(reviewNo)로 해당 리뷰를 데이터 베이스에서 수정합니다.")
	@PutMapping()
	public Object updateMovie(@RequestBody Review review) {
		ResponseEntity response = null;
		System.out.println("수정 진입");
		System.out.println(review.toString());
		final BasicResponse result = new BasicResponse();
		try {
			reviewService.modifyReview(review);
			Review rev = reviewService.getReviewbyNo(review.getReviewNo());
			result.status = true;
			result.data = review.getReviewNo() + "번 리뷰가 데이터베이스에서 수정되었습니다.";
			result.object = rev;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="리뷰의 고유번호(reviewNo)로 데이터 베이스에서 리뷰를 검색합니다.")
	@GetMapping("/byno")
	public Object getMovieByMovieCd(@RequestParam int reviewNo) {
		ResponseEntity response = null;
		System.out.println("no로 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			Review review = reviewService.getReviewbyNo(reviewNo);
			result.status = true;
			result.data = review.getReviewNo() + "번 리뷰를 호출합니다.";
			result.object = review;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="영화의 고유번호(movieCd)로 데이터 베이스에서 리뷰를 검색합니다.")
	@GetMapping("/bymoviecd")
	public Object getMovieBymovieNm(@RequestParam String movieCd) {
		ResponseEntity response = null;
		System.out.println("영화제목으로 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<Review> list = reviewService.getReviewsByMovieCd(movieCd);
			result.status = true;
			result.data = "영화리뷰를 호츌합니다.";
			result.object = list;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
}

