package com.wordchecker.exception;

public class NotAuthException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotAuthException() {
		super("unauthorized");
	}
}
