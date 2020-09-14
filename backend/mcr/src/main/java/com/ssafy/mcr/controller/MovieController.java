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
import com.ssafy.mcr.service.MovieService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/movie")
public class MovieController {


	@Autowired
	MovieService movieService;

	@ApiOperation(value="영화의 정보를 입력받아 데이터베이스에 저장합니다.")
	@PostMapping()
	public Object registMovie(@RequestBody Movie movie) {
		System.out.println("영화 입력 진입");
		System.out.println(movie.toString());
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			movieService.addMovie(movie);
			result.status = true;
			result.data = movie.getMovieNmOg() + "가 데이터베이스에 추가되었습니다.";
			result.object = movie; 
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="영화의 고유번호(movieCd)로 해당 영화정보를 데이터 베이스에서 삭제합니다.")
	@DeleteMapping()
	public Object deleteMovie(@RequestParam String movieCd) {
		ResponseEntity response = null;
		System.out.println("영화 삭제 진입");
		final BasicResponse result = new BasicResponse();
		try {
			Movie movie = movieService.getMovieByMovieCd(movieCd);
			movieService.deleteMovie(movieCd);
			result.status = true;
			result.data = movie.getMovieNmOg() + "가  데이터베이스에서 삭제되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="영화의 고유번호(movieCd)로 해당 영화정보를 데이터 베이스에서 수정합니다. (movieCd만 입력해도 사용가능)")
	@PutMapping()
	public Object updateMovie(@RequestBody Movie movie) {
		ResponseEntity response = null;
		System.out.println("수정 진입");
		final BasicResponse result = new BasicResponse();
		try {
			movieService.modifyMovie(movie);
			Movie mov = movieService.getMovieByMovieCd(movie.getMovieCd());
			result.status = true;
			result.data = movie.getMovieNmOg() + "가 데이터베이스에서 수정되었습니다.";
			result.object = mov;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="영화의 고유번호(movieCd)로 해당 영화정보를 데이터 베이스에서 검색합니다.")
	@GetMapping("/bypk")
	public Object getMovieByMovieCd(@RequestParam String movieCd) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			Movie movie = movieService.getMovieByMovieCd(movieCd);
			result.status = true;
			result.data = movie.getMovieNmOg() + "의 영화정보를 호출합니다.";
			result.object = movie;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="영화의 제목으로 데이터 베이스에서 검색합니다.")
	@GetMapping("/bytitle")
	public Object getMovieBymovieNm(@RequestParam String movieNm) {
		ResponseEntity response = null;
		System.out.println("수정 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<Movie> list = movieService.getMovieBymovieNm(movieNm);
			result.status = true;
			result.data = movieNm + "이 포함된 영화정보를 호츌합니다.";
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

