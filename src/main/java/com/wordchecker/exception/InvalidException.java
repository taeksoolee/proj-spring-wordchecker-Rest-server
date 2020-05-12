package com.wordchecker.exception;

public class InvalidException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidException() {
		super("문구 검증 오류");
	}
}
