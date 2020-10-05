package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ssafy.mcr.dto.DaumActor;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumMovieActor;
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.Paging;
import com.ssafy.mcr.dto.Ranking;
import com.ssafy.mcr.dto.SearchActor10;
import com.ssafy.mcr.dto.SearchMovie10;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumActorService;
import com.ssafy.mcr.service.DaumMovieService;
import com.ssafy.mcr.service.DaumReviewService;
import com.ssafy.mcr.service.DaumUserMovieService;
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

	@Autowired
	DaumUserMovieService daumUserMovieService;

	//sf,미스터리, 로맨스, 범죄, 액션, 스릴러, 공포, 드라마 애니메이션, 멜로, 판타지, 다큐멘터리, 어드벤쳐, 전쟁, 뮤지컬, 가족
	private static String[] genreKorea = {"멜로","미스터리","범죄","액션","스릴러","공포","드라마","애니메이션","판타지", "다큐멘터리", "전쟁", "뮤지컬", "가족"};
	private static String[] genreForeign = {"멜로","미스터리","범죄","액션","스릴러","공포","드라마","애니메이션","판타지", "다큐멘터리", "어드벤처", "전쟁", "뮤지컬", "가족"};

	private static String MovieURL = "https://movie.daum.net/moviedb/main?movieId=";
	//	@ApiOperation(value="영화를 디비에 생성합니다.")
	//	@PostMapping("/auto")
	//	public void Crawling(int start, int end) throws IOException {
	//		for(int i = start; i <= end; i++) {
	//			try {
	//				DaumMovie movie = new DaumMovie();
	//				String URL = MovieURL + i;
	//				Document doc = Jsoup.connect(URL).get();
	//				movie.setImgUrl(doc.select("span.thumb_img img").attr("src"));
	//				movie.setMovieName(doc.select("h2.tit_rel").text());
	//				movie.setMovieNameEn(doc.select("span.txt_origin").text());
	//				List<DaumReview> list = daumReviewService.getAllDaumReviewsByMovieId(i);
	//				if(list.size() == 0) {
	//					movie.setRate(0);
	//				}else {					
	//					double rate = 0;
	//					for(DaumReview review : list) {
	//						rate += review.getRate();
	//					}
	//					movie.setRate(Double.parseDouble(String.format("%.1f", rate/list.size())));
	//				}
	//				movie.setMovieId(i);
	//				movie.setOverview(doc.select("div.desc_movie p").text());
	//				if(doc.select("dl.list_movie dd.txt_main").get(0).text() != "") {					
	//					movie.setGenre(doc.select("dl.list_movie dd.txt_main").get(0).text());
	//				}
	//				movie.setNation(doc.select("dl.list_movie dd").get(1).text());
	//				Elements es = doc.select("dl.list_movie.list_main dd");
	//				for(Element e : es) {
	//					String str = e.text();
	//					if(str.length() > 4) {
	//						boolean flag = false;
	//						for(int j = 0; j < 4; j++) {
	//							if(str.charAt(j) < 48 || str.charAt(j) > 57) {
	//								flag = true;
	//							}
	//						}
	//						if(!flag) {
	//							if(str.length() >= 10) {
	//								movie.setMovieOpeningDate(str.substring(0, 10));								
	//							}else {
	//								movie.setMovieOpeningDate(str.substring(0, str.length()));																
	//							}
	//						}
	//					}else {
	//						continue;
	//					}
	//					for(int j = 0; j < str.length(); j++) {
	//						if(str.charAt(j) == '분') {
	//							for(int k = j-1; k >= 0; k--) {
	//								if(str.charAt(k) < 48 || str.charAt(k) > 57 || k == 0) {
	//									movie.setRunningTime(str.substring(k, j));
	//								}
	//							}
	//						}
	//					}
	//				}
	//				daumMovieService.addDaumMovie(movie);
	//			}catch (Exception e) {
	//				e.printStackTrace();
	//				continue;
	//			}
	//		}
	//	}

	@ApiOperation(value="해당 영화 정보를 리턴합니다.")
	@GetMapping("/byid")
	public Object SelectMovieByMovieId(@RequestParam int movieId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		//여기서 카운트올리세요
		try {
			DaumMovie movie = daumMovieService.getDaumMovieBymovieId(movieId);
			movie.setRate(Math.round((movie.getRate()) * 10) / 10.0);
			daumMovieService.addScore(movie);
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
	
	@ApiOperation(value="해당 영화 ID를 리턴합니다.")
	@GetMapping("/returnidbytitle")
	public Object SelectDaumMovieByTitle(@RequestParam String title) {
		ResponseEntity response = null;
		System.out.println("제목넣으면 아이디 호출 진입");
		final BasicResponse result = new BasicResponse();
		//여기서 카운트올리세요
		try {
			int movieId = daumMovieService.getDaumMovieIdByTitle(title);
			result.status = true;
			result.data = "success";
			result.object = movieId;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="해당 영화 정보를 리턴합니다.")
	@PostMapping("/upscore")
	public Object upScore(@RequestBody DaumMovie daumMovie) {
		ResponseEntity response = null;
		System.out.println("점수상승 메소드 진입");
		final BasicResponse result = new BasicResponse();
		//		여기서 카운트올리세요
		try {
			//			daumMovieService.addScore(daumMovie);
			DaumMovie dm = daumMovieService.getDaumMovieBymovieId(daumMovie.getMovieId());
			dm.setRate(Math.round((dm.getRate()) * 10) / 10.0);
			result.status = true;
			result.data = "success";
			result.object = dm;
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
			for(DaumMovie dm : list) {
				dm.setRate(Math.round((dm.getRate()) * 10) / 10.0);
			}
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

	@ApiOperation(value="영화를 30개씩 페이지네이션합니다.")
	@GetMapping("/page")
	public Object getMovieLimit30(@RequestParam String title, @RequestParam int page) {
		ResponseEntity response = null;
		System.out.println("페이지네이션 진입");
		final BasicResponse result = new BasicResponse();
		try {
			Paging paging = new Paging(title, page * 30);
			List<DaumMovie> list = daumMovieService.getLimit30(paging);
			for(DaumMovie dm : list) {
				dm.setRate(Math.round((dm.getRate()) * 10) / 10.0);
			}
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

	@ApiOperation(value="영화 검색 10위를 가져옵니다.")
	@GetMapping("/searchrank")
	public Object getSearchRank(@RequestParam int page) {
		List<Object> obj = new ArrayList<Object>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumMovie> list = daumMovieService.getLimit10ByScore(30 * page);
			for(DaumMovie dm : list) {
				dm.setRate(Math.round((dm.getRate()) * 10) / 10.0);
			}
			String title = "실시간 인기있는 영화들";
			SearchMovie10 movies = new SearchMovie10();
			movies.setMovies(list);
			movies.setTitle(title);
			result.status = true;
			result.data = "success";
			result.object = movies;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="영화 좋아요 10위를 가져옵니다.")
	@GetMapping("/likerank")
	public Object getLikeRank() {
		List<DaumMovie> list = new ArrayList<DaumMovie>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			List<Ranking> ranklist = daumUserMovieService.likeRank10();
			for(Ranking r : ranklist) {
				System.out.println(r.getId());
				list.add(daumMovieService.getDaumMovieBymovieId(r.getId()));
			}
			String title = "인기있는 영화 TOP 10";
			SearchMovie10 movies = new SearchMovie10();
			movies.setMovies(list);
			movies.setTitle(title);
			for(DaumMovie dm : movies.getMovies()) {
				dm.setRate(Math.round((dm.getRate()) * 10) / 10.0);
			}
			result.status = true;
			result.data = "success";
			result.object = movies;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="장르별 탑10 한국영화를 가져옵니다.")
	@GetMapping("/koreamovie")
	public Object getKoreaMovieByGenre() {
		List<DaumMovie> list = new ArrayList<DaumMovie>();
		List<Object> obj = new ArrayList<Object>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Map res = new HashMap<String, List<DaumMovie>>();
		try {
			int[] m = new int[5];
			for(int i = 0; i < 5; i++) {
				m[i] = (int)(Math.random() * genreKorea.length);
				for(int j = 0; j < i; j++) {
					if(m[i] == m[j]) {
						i--;
						break;
					}
				}
			}
			for(int i = 0; i < 5; i++) {
				list = daumMovieService.getKoreaMovieByGenre(genreKorea[m[i]]);
				for(DaumMovie tmp : list) {
					tmp.setOverview("");
					tmp.setRate(Math.round((tmp.getRate()) * 10) / 10.0);
				}
				if(genreKorea[m[i]].equals("멜로")) {
					res.put("mt"+i, "로맨스/멜로");					
				}else{
					res.put("mt"+i, genreKorea[m[i]]);					
					
				}
				res.put("mg"+i, list);
			}
			obj.add(res);
			result.status = true;
			result.data = "success";
			result.object = obj;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = false;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="장르별 탑10 외국영화를 가져옵니다.")
	@GetMapping("/foreignmovie")
	public Object getForeignMovieByGenre() {
		List<DaumMovie> list = new ArrayList<DaumMovie>();
		List<Object> obj = new ArrayList<Object>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		Map res = new HashMap<String, List<DaumMovie>>();
		try {
			int[] m = new int[5];
			for(int i = 0; i < 5; i++) {
				m[i] = (int)(Math.random() * genreForeign.length);
				for(int j = 0; j < i; j++) {
					if(m[i] == m[j]) {
						i--;
						break;
					}
				}
			}
			for(int i = 0; i < 5; i++) {
				list = daumMovieService.getForeignMovieByGenre(genreForeign[m[i]]);
				for(DaumMovie tmp : list) {
					tmp.setOverview("");
					tmp.setRate(Math.round((tmp.getRate()) * 10) / 10.0);
				}
				res.put("mg"+i, list);
				res.put("mt"+i, genreForeign[m[i]]);
			}
			obj.add(res);
			result.status = true;
			result.data = "success";
			result.object = obj;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}


}

