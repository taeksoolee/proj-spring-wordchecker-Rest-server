package com.wordchecker.exception;

public class WrongAccessException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongAccessException() {
		super("wrongAccess");
	}
}
