package com.ssafy.mcr.controller;

import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mcr.config.JwtTokenProvider;
import com.ssafy.mcr.dto.BasicResponse;
import com.ssafy.mcr.dto.User;
import com.ssafy.mcr.service.UserService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@PostMapping()
	public Object defaultLogin(@RequestBody HashMap<String, String> loginInfo, HttpServletResponse response) {
		System.out.println(loginInfo.get("id") + " " + loginInfo.get("password"));
		ResponseEntity response1 = null;
		final BasicResponse result = new BasicResponse();
		
		try {
			User user = userService.getUserbyIdandPwd(loginInfo.get("id"), loginInfo.get("password"));
			if (user == null) {
				result.status = true;
				result.data = "fail";
				response1 = new ResponseEntity<>(result, HttpStatus.OK);
				return response1;
			}
			
			System.out.println(user);
			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("jwtToken",
//					jwtTokenProvider.createToken(user.getId(), Collections.singletonList(user.getRole())));
			jsonObject.put("userinfo", userService.getUserbyId(loginInfo.get("id")));
			response.setHeader("jwsToken", jwtTokenProvider.createToken(user.getId(), Collections.singletonList(user.getRole())));
			System.out.println(response.getHeader("jwsToken"));
			result.status = true;
			result.data = "success";
			result.object = jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			result.status = true;
			result.data = "fail";
		}
		response1 = new ResponseEntity<>(result, HttpStatus.OK);
		return response1;
	}
}
