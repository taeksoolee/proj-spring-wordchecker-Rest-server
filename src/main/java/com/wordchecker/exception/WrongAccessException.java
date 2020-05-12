package com.wordchecker.exception;

public class WrongAccessException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongAccessException() {
		super("잘못된 접근 오류");
	}
}
