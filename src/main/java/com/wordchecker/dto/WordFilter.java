package com.wordchecker.dto;

public class WordFilter extends Filter{
	private int memberNo;

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "WordFilter [memberNo=" + memberNo + ", start="+ getStart() + ", length=" + getLength() + "]";
	}
}
