package com.wordchecker.exception;

// ��ȿ�� �˻� ���н� �߻��ϴ� ����
public class InvalidException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidException(String message) {
		super(message);
	}
}
