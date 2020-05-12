package com.wordchecker.exception;

public class DuplicateMemberException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateMemberException() {
		super("중복된 회원정보 존재 에러");
	}
}
