//package com.ssafy.mcr.controller;
//
////import java.io.IOException;
////import java.util.Collections;
////import java.util.List;
////import java.util.Map;
////import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
////import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
////import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.mcr.dto.Actor;
////import org.springframework.web.multipart.MultipartFile;
////
////import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.mcr.dto.BasicResponse;
//import com.ssafy.mcr.dto.User;
//import com.ssafy.mcr.service.ActorService;
//import com.ssafy.mcr.service.UserService;
//
//import io.swagger.annotations.ApiOperation;
//
////import io.swagger.annotations.ApiImplicitParam;
////import io.swagger.annotations.ApiImplicitParams;
//
//@CrossOrigin(origins = { "*" })
//@RestController
//@RequestMapping("/actor")
//public class ActorController {
//
//
//	@Autowired
//	ActorService actorService;
//	
//	@ApiOperation(value="배우의 Code를 받아 배우정보를 검색(리턴)합니다.")
//	@GetMapping()
//	public Object GetActorInfo(@RequestParam String actorCd) {
//		ResponseEntity response = null;
//		System.out.println("검색 진입");
//		System.out.println(actorCd);
//		final BasicResponse result = new BasicResponse();
//		try {
//			Actor actor = actorService.getActorbyCd(actorCd);
//			result.status = true;
//			result.data =  actor.getActorName()+ " 의 배우정보를 리턴합니다.";
//			result.object = actor;
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.status = true;
//			result.data = "fail";
//		}
//		response = new ResponseEntity<>(result, HttpStatus.OK);
//		return response;
//	}
//	
//}
//
