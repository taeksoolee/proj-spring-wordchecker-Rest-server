package com.wordchecker.exception;

// 중복회원이 있을 경우 발생하는 예외
public class DuplicateMemberException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateMemberException() {
		super("중복된 회원이 존재 합니다.");
	}
}
