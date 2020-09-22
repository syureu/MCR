package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;

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
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.DaumUserActor;
import com.ssafy.mcr.dto.DaumUserMovie;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumMovieService;
import com.ssafy.mcr.service.DaumReviewService;
import com.ssafy.mcr.service.DaumUserMovieService;
import com.ssafy.mcr.service.UserService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daumusermovie")
public class DaumUserMovieController {


	@Autowired
	DaumUserMovieService daumUserMovieService;

	@PostMapping()
	public Object insertDaumUserMovie(@RequestBody DaumUserMovie daumUserMovie) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			if(daumUserMovieService.addDaumUserMovie(daumUserMovie)==0) {
				daumUserMovieService.deletDaumUserMovie(daumUserMovie.getUserNo(), daumUserMovie.getMovieId());
			}else { //입력
			}
			result.status = true;
			result.data = "success";

		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
		
		
		
	}
//	
//	@GetMapping("/delete")
//	public Object deleteDaumUserMovie(@RequestParam int userNo,@RequestParam int personId) {
//		ResponseEntity response = null;
//		final BasicResponse result = new BasicResponse();
//		try {
//			daumUserMovieService.deletDaumUserMovie(userNo, personId);
//			result.status = true;
//			result.data = "success";
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.status = true;
//			result.data = "fail";
//		}
//		response = new ResponseEntity<>(result, HttpStatus.OK);
//		return response;
//	} 

	@GetMapping()
	public Object SelectMovieByUserNo(@RequestParam int userNo) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumUserMovie> list = daumUserMovieService.getDaumUserMovieByUserNo(userNo);
			result.status = true;
			result.data = "success";
			result.object = list;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/check")
	public Object checkLike(@RequestParam int userNo, @RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			DaumUserMovie dua = daumUserMovieService.checkLike(userNo, movieId);
			int check = 0;
			if(dua != null) {
				check = 1;
			}
			result.status = true;
			result.data = "success";
			result.object = check;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}

