package com.ssafy.mcr.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mcr.dto.MovieData;

public class MovieAPI2 {

	static HashMap<String, Object> movieListResult = new HashMap<String, Object>();
	static final String URL ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleList.json?key=74c39c052c622cb3cb5b1605714de8a5";
	static final String URLpage ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=74c39c052c622cb3cb5b1605714de8a5&curPage=";
	static String itemPerPage ="&itemPerPage=100";

//	public static Object totalCnt() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		//itemPerPage=78208
//		Map<String, Object> data = mapper.readValue(
//				new URL(URL),
//				new TypeReference<Map<String,Object>>(){});
//
//		Map<String, Object> movieListResult = (Map<String, Object>) data.get("movieListResult");
//		return movieListResult.get("totCnt");
//	}
	
	@GetMapping("/GetkobisData")
	public static void callAPI(int curPage) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//itemPerPage=78208
		Map<String, Object> data = mapper.readValue(
				new URL(URLpage+curPage+itemPerPage),
				new TypeReference<Map<String,Object>>(){});

		Map<String, Object> peopleListResult = (Map<String, Object>) data.get("peopleListResult");
		System.out.println(peopleListResult.toString());

	}
	public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
//		int page = Integer.parseInt(totalCnt().toString())/100;
		for(int i = 1; i <= 10; i++) {
			callAPI(i);			
		}
		
	}
}
