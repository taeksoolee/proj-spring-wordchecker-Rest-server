package com.wordchecker.exception;

public class DevMistakeException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DevMistakeException(String message) {
		super(message);
	}
	
}
