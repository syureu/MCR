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
import com.ssafy.mcr.dto.DaumMoviePhoto;
import com.ssafy.mcr.service.DaumActorService;
import com.ssafy.mcr.service.DaumMovieActorService;
import com.ssafy.mcr.service.DaumMoviePhotoService;
import com.ssafy.mcr.service.DaumMovieService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/daummoviephoto")
public class DaumMoviePhotoController {


	@Autowired
	DaumMoviePhotoService daumMoviePhotoService;

	@Autowired
	DaumMovieService daumMovieService;

	private static String MovieURL = "https://movie.daum.net/moviedb/photoviewer?id=";
	@ApiOperation(value="해당영화의 사진목록을 디비에 저장합니다.")
	@PostMapping("/auto")
	public void Crawling(int start, int end) throws IOException {
		for(int i = start; i <= end; i++) {
			try {
				System.out.println(i);
				String URL = MovieURL + i + "#1/PhotoList";
				System.out.println(URL);
				Document doc = Jsoup.connect(URL).get();
				System.out.println(doc.toString());
				Elements eles = doc.select("div.item");
				for(Element e : eles) {
					System.out.println(e.toString());
				}
			}catch (Exception e) {
				continue;
			}
		}
	}

	@ApiOperation(value="해당 영화의 사진 목록를 리턴합니다.")
	@GetMapping("/photolist")
	public Object SelectActorList(@RequestParam int movieId) {
		List<DaumMoviePhoto> list = null;
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		System.out.println("사진목록 검색 진입");
		try {
			list = daumMoviePhotoService.getDaumMoviePhotoBymovieId(movieId);
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

