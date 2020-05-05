package com.wordchecker.exception;

// 유효성 검사 실패시 발생하는 예외
public class InvalidException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidException(String message) {
		super(message);
	}
}
