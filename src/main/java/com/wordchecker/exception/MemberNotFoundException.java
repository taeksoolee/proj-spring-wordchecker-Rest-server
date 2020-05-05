package com.wordchecker.exception;

//회원 정보를 찾을 수 없을 때 발생되는 예외
public class MemberNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MemberNotFoundException(String message) {
		super(message);
	}
}
