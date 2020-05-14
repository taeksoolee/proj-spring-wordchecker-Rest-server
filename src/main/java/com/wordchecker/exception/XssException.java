package com.wordchecker.exception;

public class XssException extends Exception{
	private static final long serialVersionUID = 1L;

	public XssException() {
		super("xss");
	}
}
