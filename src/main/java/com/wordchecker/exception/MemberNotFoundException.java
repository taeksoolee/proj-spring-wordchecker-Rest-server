package com.wordchecker.exception;

public class MemberNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MemberNotFoundException() {
		super("memeberNotFound");
	}
}
