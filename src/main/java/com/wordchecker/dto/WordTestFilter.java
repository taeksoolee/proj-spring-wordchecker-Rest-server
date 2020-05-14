package com.wordchecker.dto;

import com.wordchecker.util.WordOrderType;

public class WordTestFilter{
	private int count;
	private String startDate;
	private String endDate;
	private String speling;
	private String meaning;
	private int memberNo;
	private WordOrderType wordOrderType;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
		return "WordTestFilter [count=" + count + "startDate=" + startDate + ", endDate=" + endDate + ", speling=" + speling + ", meaning="
				+ meaning + ", memberNo=" + memberNo + ", wordOrderType=" + wordOrderType + "]";
	}
}