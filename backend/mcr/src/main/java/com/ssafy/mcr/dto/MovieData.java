package com.ssafy.mcr.dto;

import java.util.List;

public class MovieData {
	String movieCd;
	String movieNm;
	String movieNmEn;
	int prdtYear;
	int openDt;
	String typeNm;
	String repNationNm;
	String repGenreNm;
	List<Directors> directors;
	List<Companys> companys;
	public String getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getMovieNmEn() {
		return movieNmEn;
	}
	public void setMovieNmEn(String movieNmEn) {
		this.movieNmEn = movieNmEn;
	}
	public int getPrdtYear() {
		return prdtYear;
	}
	public void setPrdtYear(int prdtYear) {
		this.prdtYear = prdtYear;
	}
	public int getOpenDt() {
		return openDt;
	}
	public void setOpenDt(int openDt) {
		this.openDt = openDt;
	}
	public String getTypeNm() {
		return typeNm;
	}
	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}
	public String getRepNationNm() {
		return repNationNm;
	}
	public void setRepNationNm(String repNationNm) {
		this.repNationNm = repNationNm;
	}
	public String getRepGenreNm() {
		return repGenreNm;
	}
	public void setRepGenreNm(String repGenreNm) {
		this.repGenreNm = repGenreNm;
	}
	public List<Directors> getDirectors() {
		return directors;
	}
	public void setDirectors(List<Directors> directors) {
		this.directors = directors;
	}
	public List<Companys> getCompanys() {
		return companys;
	}
	public void setCompanys(List<Companys> companys) {
		this.companys = companys;
	}
	@Override
	public String toString() {
		return "MovieData [movieCd=" + movieCd + ", movieNm=" + movieNm + ", movieNmEn=" + movieNmEn + ", prdtYear="
				+ prdtYear + ", openDt=" + openDt + ", typeNm=" + typeNm + ", repNationNm=" + repNationNm
				+ ", repGenreNm=" + repGenreNm + ", directors=" + directors + ", companys=" + companys + "]";
	}
	
}
