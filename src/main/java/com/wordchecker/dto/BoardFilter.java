package com.wordchecker.dto;

public class BoardFilter extends Filter{
	private String title;
	private String content;
	private String startDate;
	private String endDate;
	private String nickname;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "BoardFilter [title=" + title + ", contetnt=" + content + ", startDate=" + startDate  +", endDate=" + endDate + ", nickname="
				+ nickname + ", start="+ getStart() + ", length=" + getLength() + "]";
	}
}
