package com.wordchecker.exception;

// �ߺ�ȸ���� ���� ��� �߻��ϴ� ����
public class DuplicateEntityException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateEntityException(String message) {
		super(message);
	}
}
