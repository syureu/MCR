package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumReviewService;
import com.ssafy.mcr.service.UserService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daumreview")
public class DaumReviewController {


	@Autowired
	DaumReviewService daumReviewService;

	private static String reviewURL = "https://movie.daum.net/moviedb/grade?movieId=";

	@ApiOperation(value="리뷰를 생성합니다.")
	@PostMapping()
	public Object registReview(@RequestBody DaumReview daumReview) {
		System.out.println("생성 진입");
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			daumReviewService.addDaumReview(daumReview);
			result.status = true;
			result.data = "success";
			result.object = daumReview;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="리뷰를 생성합니다.")
	@PostMapping("/auto")
	public void Crawling(int start, int end) throws IOException {
		for(int i = start; i <= end; i++) {
			try {
				String URL = reviewURL + start + "&type=netizen&page=2000";
				Document doc = Jsoup.connect(URL).get();
				Elements p = doc.select("a.link_page");
				String pk = "";
				for(Element e : p) {
					pk = e.text();
				}
				int maxpage = Integer.parseInt(pk);
				for(int j = 1; j <= maxpage; j++) {
					URL = reviewURL + i + "&type=netizen&page=" + j;
					doc = Jsoup.connect(URL).get();
					Elements reviewInfos = doc.select("div.review_info");
					for(Element e : reviewInfos) {
						DaumReview review = new DaumReview();
						review.setMovie_id(start);
						review.setWriter(e.select("em.link_profile").text());
						review.setRate((Integer.parseInt(e.select("em.emph_grade").text())));
						review.setContent((e.select("p.desc_review").text()));
						review.setRegtime((e.select("span.info_append").text()));
						daumReviewService.addDaumReview(review);
					}
				}
			}catch (Exception e) {
				continue;
			}
		}
	}

	@ApiOperation(value="해당 영화의 리뷰목록을 불러옵니다.")
	@GetMapping()
	public Object SelectUser(@RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumReview> list = daumReviewService.getDaumReviewsByMovieId(movieId);
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

}

