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

public class MovieAPI {

	static HashMap<String, Object> movieListResult = new HashMap<String, Object>();
	static final String URL ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=74c39c052c622cb3cb5b1605714de8a5";
	static final String URLpage ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=74c39c052c622cb3cb5b1605714de8a5&curPage=";
	static String itemPerPage ="&itemPerPage=100";

	public static Object totalCnt() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//itemPerPage=78208
		Map<String, Object> data = mapper.readValue(
				new URL(URL),
				new TypeReference<Map<String,Object>>(){});

		Map<String, Object> movieListResult = (Map<String, Object>) data.get("movieListResult");
		return movieListResult.get("totCnt");
	}
	
	@GetMapping("/GetkobisData")
	public static void callAPI(int curPage) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//itemPerPage=78208
		Map<String, Object> data = mapper.readValue(
				new URL(URLpage+curPage+itemPerPage),
				new TypeReference<Map<String,Object>>(){});

		Map<String, Object> movieListResult = (Map<String, Object>) data.get("movieListResult");
		List<Object> list = (List<Object>) movieListResult.get("movieList");
		for(Object o : list) {
			Map<String, Object> tmp = (Map<String, Object>)o;
			String movieDetailUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=74c39c052c622cb3cb5b1605714de8a5&movieCd="+tmp.get("movieCd");
//			System.out.println(curPage + " : " + tmp.get("movieCd"));
			Map<String, Object> detail = mapper.readValue(
					new URL(movieDetailUrl),
					new TypeReference<Map<String,Object>>(){});
			Map<String, Object> movieInfoResult = (Map<String, Object>) detail.get("movieInfoResult");
			Map<String, Object> movieInfo = (Map<String, Object>) movieInfoResult.get("movieInfo");
			System.out.println(movieInfo.get("movieCd"));
			System.out.println(movieInfo.get("movieNm"));
			System.out.println(movieInfo.get("movieNmEn"));
			System.out.println(movieInfo.get("movieNmOg"));
			System.out.println(movieInfo.get("showTm"));
			System.out.println(movieInfo.get("prdtYear"));
			System.out.println(movieInfo.get("openDt"));
			System.out.println(tmp.get("repNationNm"));
			System.out.println(tmp.get("repGenreNm"));
			List<Object> directors = (List<Object>) movieInfo.get("directors");
			for(Object s : directors) {
				Map<String, Object> tmps = (Map<String, Object>)s;
				System.out.println(tmps.get("peopleNm"));
				System.out.println(tmps.get("peopleNmEn"));
			}
			List<Object> actors = (List<Object>) movieInfo.get("actors");
			for(Object s : actors) {
				Map<String, Object> tmps = (Map<String, Object>)s;
				System.out.println(tmps.get("peopleNm"));
				System.out.println(tmps.get("peopleNmEn"));
				System.out.println(tmps.get("cast"));
				System.out.println(tmps.get("castEn"));
			}
			
			System.out.println("==============================================");
		}
		

	}
	public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		int page = Integer.parseInt(totalCnt().toString())/100;
		for(int i = 1; i <= page; i++) {
			callAPI(i);			
		}
		
	}
}
