package com.wordchecker.exception;

public class MemberNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MemberNotFoundException() {
		super("회원정보 찾을수 없습니다.");
	}
}
