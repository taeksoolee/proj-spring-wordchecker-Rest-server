package com.wordchecker.exception;

// ������ �ʴ� ������ �õ��� �� �߻��ϴ� ����
public class WrongAccessException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongAccessException(String message) {
		super(message);
	}
}
