package com.wordchecker.exception;

// 중복회원이 있을 경우 발생하는 예외
public class DuplicateEntityException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateEntityException(String message) {
		super(message);
	}
}
