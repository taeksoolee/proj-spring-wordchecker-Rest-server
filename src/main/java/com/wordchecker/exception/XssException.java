package com.wordchecker.exception;

public class XssException extends Exception{
	private static final long serialVersionUID = 1L;

	public XssException() {
		super("입력 내용에 태그 사용");
	}
}
