package com.ssafy.mcr.controller;

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
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.UserService;

import io.swagger.annotations.ApiOperation;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//http://localhost:3000/#/Signup
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	UserService userService;

	@ApiOperation(value="회원의 정보를 받아 회원정보를 생성(가입)합니다.")
	@PostMapping("/post")
	public Object sign(@RequestBody User user) {
		System.out.println("생성 진입");
		System.out.println(user.toString());
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		try {
			userService.addUser(user);
			result.status = true;
			result.data = user.getId() + " 회원이 추가되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="회원의 ID를 받아 회원정보를 삭제합니다.")
	@DeleteMapping("/delete")
	public Object deleteUser(@RequestParam String id) {
		ResponseEntity response = null;
		System.out.println("삭제 진입");
		final BasicResponse result = new BasicResponse();
		try {
			userService.deleteUser(id);
			result.status = true;
			result.data = id + " 회원이 삭제되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="회원의 ID를 받아 회원정보를 수정합니다.")
	@PutMapping("/put")
	public Object UpdateUser(@RequestBody User user) {
		ResponseEntity response = null;
		System.out.println("수정 진입");
		final BasicResponse result = new BasicResponse();
		try {
			userService.modifyUser(user);
			result.status = true;
			result.data = user.getId() + " 회원이 수정되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="회원의 ID를 받아 회원정보를 검색(리턴)합니다")
	@GetMapping("/get")
	public Object UpdateUser(@RequestParam String id) {
		ResponseEntity response = null;
		System.out.println("수정 진입");
		final BasicResponse result = new BasicResponse();
		try {
			User user = userService.getUserbyId(id);
			result.status = true;
			result.data = id + " 회원정보를 리턴합니다.";
			result.object = user;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
}

