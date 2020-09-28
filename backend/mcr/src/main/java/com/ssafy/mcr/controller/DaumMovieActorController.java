package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mcr.dto.BasicResponse;
import com.ssafy.mcr.dto.DaumActor;
import com.ssafy.mcr.dto.DaumMovie;
import com.ssafy.mcr.dto.DaumMovieActor;
import com.ssafy.mcr.dto.Ranking;
import com.ssafy.mcr.dto.SearchActor10;
import com.ssafy.mcr.dto.SearchMovie10;
import com.ssafy.mcr.service.DaumActorService;
import com.ssafy.mcr.service.DaumMovieActorService;
import com.ssafy.mcr.service.DaumMovieService;
import com.ssafy.mcr.service.DaumUserActorService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daummovieactor")
public class DaumMovieActorController {


	@Autowired
	DaumMovieActorService daumMovieActorService;

	@Autowired
	DaumMovieService daumMovieService;

	@Autowired
	DaumActorService daumActorService;
	
	@Autowired
	DaumUserActorService daumUserActorService;


//	private static String MovieURL = "https://movie.daum.net/moviedb/crew?movieId=";
//	@ApiOperation(value="영화_배우 컬럼를 디비에 생성합니다.")
//	@PostMapping("/auto")
//	public void Crawling(int start, int end) throws IOException {
//		for(int i = start; i <= end; i++) {
//			try {
//				System.out.println(i);
//				String URL = MovieURL + i;
//				Document doc = Jsoup.connect(URL).get();
//				Elements eles = doc.select("div.movie_join");
//				for(Element e : eles) {
//					DaumMovieActor movieActor = new DaumMovieActor();
//					if(e.select("h4.tit_movie").text().equals("감독")) {
//						Elements list = e.select("ul.list_join.list_staff").select("a");
//						for(Element l : list) {
//							movieActor.setMovieId(i);
//							movieActor.setPersonId(Integer.parseInt(l.select("a").attr("data-tiara-id")));
//							movieActor.setCasting("감독");
//							movieActor.setCheck(0);
//							System.out.println(movieActor.toString());
//							daumMovieActorService.addDaumMovieActor(movieActor);
//						}
//					}else if(e.select("h4.tit_movie").text().equals("주연")) {
//						Elements list = e.select("ul.list_join.list_staff").select("a");
//						for(Element l : list) {
//							movieActor.setMovieId(i);
//							movieActor.setPersonId(Integer.parseInt(l.select("a").attr("data-tiara-id")));
//							movieActor.setCasting(l.select("span.txt_join").text());
//							movieActor.setCheck(1);
//							daumMovieActorService.addDaumMovieActor(movieActor);
//						}
//					}
//				}
//				eles = doc.select("div.movie_join.movie_staff");
//				for(Element e : eles) {
//					DaumMovieActor movieActor = new DaumMovieActor();
//					if(e.select("h4.tit_movie").text().equals("출연")) {
//						Elements list = e.select("ul.list_join.list_staff").select("a");
//						for(Element l : list) {
//							movieActor.setMovieId(i);
//							movieActor.setPersonId(Integer.parseInt(l.select("a").attr("data-tiara-id")));
//							movieActor.setCasting(l.select("span.txt_join").text());
//							movieActor.setCheck(2);	
//							daumMovieActorService.addDaumMovieActor(movieActor);
//						}
//					}
//				}
//				eles = doc.select("dl.list_produce");
//				for(Element e : eles) {
//					Elements list = e.select("span.info_person a");
//					for(Element l : list) {
//						DaumMovieActor movieActor = new DaumMovieActor();
//						movieActor.setMovieId(i);
//						movieActor.setPersonId(Integer.parseInt(l.select("a").attr("data-tiara-id")));
//						movieActor.setCasting(e.select("dt").text());
//						movieActor.setCheck(3);	
//						daumMovieActorService.addDaumMovieActor(movieActor);
//					}
//				}
//			}catch (Exception e) {
//				continue;
//			}
//		}
//	}

	@ApiOperation(value="해당 영화의 영화인 목록를 리턴합니다.")
	@GetMapping("/actorlist")
	public Object SelectActorList(@RequestParam int movieId) {
		List<DaumMovieActor> list = null;
		List<Object> obj = new ArrayList<Object>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		ObjectMapper mapper = new ObjectMapper();
		Map res = null;
		System.out.println("영화인목록 검색 진입");
		try {
			list = daumMovieActorService.getDaumMovieActorBymovieId(movieId);
//			int cnt = 0;
			for(DaumMovieActor ma : list) {
//				if(cnt == 2) {
//					break;
//				}
				DaumActor da = daumActorService.getDaumActorBypersonId(ma.getPersonId());
				System.out.println(ma.getPersonId());
				String actorName = da.getActorName();
				String imgUrl = da.getImgUrl();
				res = mapper.convertValue(ma, Map.class);
				res.put("actorName", actorName);
				res.put("imgUrl", imgUrl);
				obj.add(res);
//				cnt++;
			}
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

	@ApiOperation(value="해당 영화인의 필모그래피를 리턴합니다.")
	@GetMapping("/filomography")
	public Object SelectMovieList(@RequestParam int personId) {
		List<DaumMovieActor> list = null;
		List<Object> obj = new ArrayList<Object>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		ObjectMapper mapper = new ObjectMapper();
		Map res = null;
		System.out.println("영화필모그래피 검색 진입");
		try {
			list = daumMovieActorService.getDaumMovieActorBypersonId(personId);
//			System.out.println(list.toString()+ " " + list.size());
			for(DaumMovieActor ma : list) {
				DaumMovie dm = daumMovieService.getDaumMovieBymovieId(ma.getMovieId());
				if(dm == null) {
					continue;
				}
				String movieName = dm.getMovieName();
				String imgUrl = dm.getImgUrl();
				res = mapper.convertValue(ma, Map.class);
				res.put("movieName", movieName);
				res.put("imgUrl", imgUrl);
				obj.add(res);
			}
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

