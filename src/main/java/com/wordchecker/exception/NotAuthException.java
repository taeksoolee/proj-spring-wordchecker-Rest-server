package com.wordchecker.exception;

// �α��ε��� �ʾ����� ����
public class NotAuthException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotAuthException() {
		super("�α��� ���ּ���.");
	}
}
