package com.ssafy.mcr.dto;

public class Companys {
	private String companyCd;
	private String companyNm;
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	@Override
	public String toString() {
		return "Companys [companyCd=" + companyCd + ", companyNm=" + companyNm + "]";
	}
	
	
}
