package com.ssafy.mcr.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.Ranking;
import com.ssafy.mcr.dto.SearchActor10;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.DaumActorService;
import com.ssafy.mcr.service.DaumReviewService;
import com.ssafy.mcr.service.DaumUserActorService;
import com.ssafy.mcr.service.UserService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daumactor")
public class DaumActorController {


	@Autowired
	DaumActorService daumActorService;

	@Autowired
	DaumUserActorService daumUserActorService;
	//	private static String ActorURL = "https://movie.daum.net/person/main?personId=";
	//
	//	@ApiOperation(value="배우를 생성합니다.")
	//	@PostMapping("/auto")
	//	public void Crawling(int start, int end) throws IOException {
	//		for(int i = start; i <= end; i++) {
	//			try {
	//			DaumActor actor = new DaumActor();
	//			String URL = ActorURL + i;
	//				Document doc = Jsoup.connect(URL).get();
	//				actor.setImgUrl(doc.select("img.thumb_g").attr("src"));
	//				Elements summary = doc.select("div.movie_summary dl");
	//				for(Element e : summary) {
	//					if(e.select("dt").text().equals("직업")){
	//						actor.setJob(e.select("dd").text());
	//					}else if(e.select("dt").text().equals("출생")) {
	//						actor.setBirth(e.select("dd").text());						
	//					}else if(e.select("dt").text().equals("성별")) {
	//						actor.setGender(e.select("dd").text());						
	//					}
	//				}
	//				actor.setActorName(doc.select("strong.tit_movie").text());
	//				actor.setActorNameEn(doc.select("span.txt_movie").text());
	//				actor.setPersonId(i);
	//				daumActorService.addDaumActor(actor);
	//			}catch (Exception e) {
	//				continue;
	//			}
	//		}
	//	}

	@ApiOperation(value="해당 배우 정보를 리턴합니다.")
	@GetMapping()
	public Object SelectUser(@RequestParam int personId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			DaumActor actor = daumActorService.getDaumActorBypersonId(personId);
			result.status = true;
			result.data = "success";
			result.object = actor;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value="배우 좋아요 10위를 가져옵니다.")
	@GetMapping("/likerank")
	public Object getLikeRank() {
		List<DaumActor> list = new ArrayList<DaumActor>();
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			List<Ranking> ranklist = daumUserActorService.likeRank10();
			for(Ranking r : ranklist) {
				System.out.println(r.getId());
				list.add(daumActorService.getDaumActorBypersonId(r.getId()));
			}
			String title = "인기있는 배우 TOP 10";
			SearchActor10 actors = new SearchActor10();
			actors.setActors(list);
			actors.setTitle(title);
			result.status = true;
			result.data = "success";
			result.object = actors;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}

