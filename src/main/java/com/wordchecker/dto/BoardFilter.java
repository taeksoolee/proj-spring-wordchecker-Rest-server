package com.wordchecker.dto;

public class BoardFilter extends Filter{
	private String title;
	private String contetnt;
	private String writeDate;
	private String nickname;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContetnt() {
		return contetnt;
	}
	public void setContetnt(String contetnt) {
		this.contetnt = contetnt;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "BoardFilter [title=" + title + ", contetnt=" + contetnt + ", writeDate=" + writeDate + ", nickname="
				+ nickname + ", start="+ getStart() + ", length=" + getLength() + "]";
	}
}
