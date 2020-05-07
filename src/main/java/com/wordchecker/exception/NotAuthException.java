package com.wordchecker.exception;

// 로그인되지 않았을떄 예외
public class NotAuthException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotAuthException() {
		super("로그인 해주세요.");
	}
}
