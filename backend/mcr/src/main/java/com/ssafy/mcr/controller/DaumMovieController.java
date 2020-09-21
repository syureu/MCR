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
import com.ssafy.mcr.dto.DaumActor;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumActorService;
import com.ssafy.mcr.service.DaumMovieService;
import com.ssafy.mcr.service.DaumReviewService;
import com.ssafy.mcr.service.UserService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daummovie")
public class DaumMovieController {


	@Autowired
	DaumMovieService daumMovieService;

	@Autowired
	DaumReviewService daumReviewService;

	private static String MovieURL = "https://movie.daum.net/moviedb/main?movieId=";
	@ApiOperation(value="영화를 디비에 생성합니다.")
	@PostMapping("/auto")
	public void Crawling(int start, int end) throws IOException {
		for(int i = start; i <= end; i++) {
			try {
				System.out.println(i);
				DaumMovie movie = new DaumMovie();
				String URL = MovieURL + i;
				Document doc = Jsoup.connect(URL).get();
				movie.setImgUrl(doc.select("span.thumb_img img").attr("src"));
				movie.setMovieName(doc.select("h2.tit_rel").text());
				movie.setMovieNameEn(doc.select("span.txt_origin").text());
				List<DaumReview> list = daumReviewService.getAllDaumReviewsByMovieId(i);
				double rate = 0;
				for(DaumReview review : list) {
					rate += review.getRate();
				}
				movie.setRate(Double.parseDouble(String.format("%.1f", rate/list.size())));
				movie.setMovieId(i);
				movie.setOverview(doc.select("div.desc_movie p").text());
				movie.setGenre(doc.select("dl.list_movie dd.txt_main").get(0).text());
				movie.setNation(doc.select("dl.list_movie dd").get(1).text());
				Elements es = doc.select("dl.list_movie.list_main dd");
				for(Element e : es) {
					String str = e.text();
					if(str.length() > 4) {
						boolean flag = false;
						for(int j = 0; j < 4; j++) {
							if(str.charAt(j) < 48 || str.charAt(j) > 57) {
								flag = true;
							}
						}
						if(!flag) {
							movie.setMovieOpeningDate(str.substring(0, 10));
						}
					}else {
						continue;
					}
					for(int j = 0; j < str.length(); j++) {
						if(str.charAt(j) == '분') {
							for(int k = j-1; k >= 0; k--) {
								if(str.charAt(k) < 48 || str.charAt(k) > 57 || k == 0) {
									movie.setRunningTime(str.substring(k, j));
								}
							}
						}
					}
				}
				daumMovieService.addDaumMovie(movie);
			}catch (Exception e) {
				continue;
			}
		}
	}

	@ApiOperation(value="해당 영화 정보를 리턴합니다.")
	@GetMapping("/byid")
	public Object SelectMovieByMovieId(@RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			DaumMovie movie = daumMovieService.getDaumMovieBymovieId(movieId);
			result.status = true;
			result.data = "success";
			result.object = movie;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="제목으로 검색한 영화 정보들을 리턴합니다.")
	@GetMapping("/bytitle")
	public Object SelectMovieByTitle(@RequestParam String title) {
		ResponseEntity response = null;
		System.out.println("제목 검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumMovie> list = daumMovieService.getDaumMovieByTitle(title);
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

