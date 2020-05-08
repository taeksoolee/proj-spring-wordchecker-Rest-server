package com.wordchecker.dto;

import com.wordchecker.util.WordOrderType;

public class WordTestFilter{
	private String StartDate;
	private String endDate;
	private String speling;
	private String meaning;
	private int memberNo;
	private WordOrderType wordOrderType;
	
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSpeling() {
		return speling;
	}
	public void setSpeling(String speling) {
		this.speling = speling;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public WordOrderType getWordOrderType() {
		return wordOrderType;
	}
	public void setWordOrderType(WordOrderType wordOrderType) {
		this.wordOrderType = wordOrderType;
	}
	
	@Override
	public String toString() {
		return "WordTestFilter [StartDate=" + StartDate + ", endDate=" + endDate + ", speling=" + speling + ", meaning="
				+ meaning + ", memberNo=" + memberNo + ", wordOrderType=" + wordOrderType + "]";
	}
}