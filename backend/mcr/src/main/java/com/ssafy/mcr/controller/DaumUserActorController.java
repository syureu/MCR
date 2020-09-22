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
import com.ssafy.mcr.dto.DaumReview;
import com.ssafy.mcr.dto.DaumUserActor;
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
@RequestMapping("/daumuseractor")
public class DaumUserActorController {


	@Autowired
	DaumUserActorService daumUserActorService;

	@PostMapping()
	public Object insertDaumUserActor(@RequestBody DaumUserActor daumUserActor) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			if(daumUserActorService.addDaumUserActor(daumUserActor)==0) {
				daumUserActorService.deletDaumUserActor(daumUserActor.getUserNo(), daumUserActor.getPersonId());
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
//	public Object deleteDaumUserActor(@RequestParam int userNo,@RequestParam int personId) {
//		ResponseEntity response = null;
//		final BasicResponse result = new BasicResponse();
//		try {
//			daumUserActorService.deletDaumUserActor(userNo, personId);
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

	@GetMapping("/list")
	public Object SelectActorByUserNo(@RequestParam int userNo) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			List<DaumUserActor> list = daumUserActorService.getDaumUserActorByUserNo(userNo);
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
	public Object checkLike(@RequestParam int userNo, @RequestParam int personId) {
		ResponseEntity response = null;
		System.out.println("검색 진입");
		final BasicResponse result = new BasicResponse();
		try {
			DaumUserActor dua = daumUserActorService.checkLike(userNo, personId);
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

