package com.wordchecker.exception;

// 허용되지 않는 접근을 시도할 떄 발생하는 예외
public class WrongAccessException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongAccessException(String message) {
		super(message);
	}
}
