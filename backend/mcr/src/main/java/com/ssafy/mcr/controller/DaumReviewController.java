package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mcr.dto.BasicResponse;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumMovieActor;
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumMovieService;
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
	UserService daumUserService;
	
	@Autowired
	DaumReviewService daumReviewService;
	
	@Autowired
	DaumMovieService daumMovieService;

	private static String reviewURL = "https://movie.daum.net/moviedb/grade?movieId=";

	@ApiOperation(value="리뷰를 생성합니다.")
	@PostMapping()
	public Object registReview(@RequestBody DaumReview daumReview) {
		System.out.println("생성 진입");
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			Object time = (Object) System.currentTimeMillis();
			User user = daumUserService.getUserbyNo(daumReview.getUserNo());
			DaumMovie dm = daumMovieService.getDaumMovieBymovieId(daumReview.getMovieId());
			int count = dm.getCount();
			double rate = dm.getRate();
			double tmp = (rate * count + daumReview.getRate())/(count+1);
			dm.setRate(tmp);
			dm.setCount(count+1);
			daumReview.setRegtime(time.toString());
			daumReview.setWriter(user.getUserid());
			daumReviewService.addDaumReview(daumReview);
			daumMovieService.updateDaumMovieRate(dm);
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

//	@ApiOperation(value="리뷰를 생성합니다.")
//	@PostMapping("/auto")
//	public void Crawling(int start, int end) throws IOException {
//		loop:
//		for(int i = start; i <= end; i++) {
//			int cnt = 0;
//			try {
//				System.out.println(i);
//				String URL = reviewURL + i + "&type=netizen&page=2000";
//				Document doc = Jsoup.connect(URL).get();
//				Elements p = doc.select("a.link_page");
//				String pk = "";
//				for(Element e : p) {
//					pk = e.text();
//				}
//				int maxpage = Integer.parseInt(pk);
//				for(int j = 1; j <= maxpage; j++) {
//					URL = reviewURL + i + "&type=netizen&page=" + j;
//					doc = Jsoup.connect(URL).get();
//					Elements reviewInfos = doc.select("div.review_info");
//					for(Element e : reviewInfos) {
//						if(cnt == 100) {
//							continue loop;
//						}
//						DaumReview review = new DaumReview();
//						review.setMovieId(i);
//						review.setWriter(e.select("em.link_profile").text());
//						review.setRate((Integer.parseInt(e.select("em.emph_grade").text())));
//						review.setContent((e.select("p.desc_review").text()));
//						review.setRegtime((e.select("span.info_append").text()));
//						daumReviewService.addDaumReview(review);
//						cnt++;
//					}
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//				continue;
//			}
//		}
//	}

	@ApiOperation(value="해당 영화의 리뷰를 수정합니다.")
	@PutMapping("/update")
	public Object updateReview(@RequestBody DaumReview daumReview) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {			
			DaumReview target = daumReviewService.getDaumReviewByNo(daumReview.getMovieId(), daumReview.getUserNo());
			daumReviewService.updateDaumReview(daumReview);
			DaumMovie dm = daumMovieService.getDaumMovieBymovieId(daumReview.getMovieId());
			int count = dm.getCount();
			double rate = dm.getRate();
			double tmp = (rate * count - target.getRate() + daumReview.getRate())/(count);
			dm.setRate(tmp);
			daumMovieService.updateDaumMovieRate(dm);
			result.status = true;
			result.data = "success";
			result.object = target;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="해당 영화의 긍정리뷰목록을 불러옵니다.")
	@GetMapping("/pos")
	public Object SelectPosReviews(@RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("긍정리뷰 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumReview> list = daumReviewService.getDaumPosReviewsByMovieId(movieId);
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
	
	@ApiOperation(value="해당 영화의 부정리뷰목록을 불러옵니다.")
	@GetMapping("/neg")
	public Object SelectNegReviews(@RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("부정리뷰 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumReview> list = daumReviewService.getDaumNagReviewsByMovieId(movieId);
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
	
	@ApiOperation(value="해당 영화의 리뷰목록을 불러옵니다.")
	@GetMapping("/all")
	public Object SelectAllReviews(@RequestParam int movieId) {
		List<DaumReview> listPos = null;
		ResponseEntity response = null;
		ObjectMapper mapper = new ObjectMapper();
		Map res = null;
		System.out.println("리뷰 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			
			List<DaumReview> list = daumReviewService.getAllDaumReviewsByMovieId(movieId);
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
	
	@ApiOperation(value="해당 영화의 리뷰목록을 불러옵니다.")
	@GetMapping("/check")
	public Object SelectAllReviews(@RequestParam int movieId, @RequestParam int userNo) {
		System.out.println("체크진입");
		ResponseEntity response = null;
		ObjectMapper mapper = new ObjectMapper();
		Map res = null;
		final BasicResponse result = new BasicResponse();
		try {
			DaumReview dr = daumReviewService.getDaumReviewByNo(movieId, userNo);
			result.data = "success";
			result.status = true;
			result.object = dr;				
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="해당 영화의 리뷰목록을 불러옵니다.")
	@PostMapping("/count")
	public Object insertCount(int start , int end) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			for(int i = start; i <= end; i++) {				
				daumReviewService.insertCount(i);
			}
			result.data = "success";
			result.status = true;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="해당 리뷰 삭제")
	@DeleteMapping()
	public Object deleteDaumReview(int movieId, int userNo) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			DaumReview dr = daumReviewService.getDaumReviewByNo(movieId, userNo);
			DaumMovie dm = daumMovieService.getDaumMovieBymovieId(dr.getMovieId());
			double prerate = dm.getRate();
			int precount = dm.getCount();
			double tmp = (prerate * precount - dr.getRate())/(precount-1);
			System.out.println(tmp);
			dm.setRate(tmp);
			dm.setCount(precount-1);
			daumMovieService.updateDaumMovieRate(dm);
			daumReviewService.deleteDaumReview(movieId, userNo);
			result.status = true;
			result.data = "삭제 성공";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "삭제 실패";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

}

