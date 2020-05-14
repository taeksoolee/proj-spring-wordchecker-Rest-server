package com.wordchecker.exception;

public class DuplicateMemberException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateMemberException() {
		super("duplicateMemberExist");
	}
}
