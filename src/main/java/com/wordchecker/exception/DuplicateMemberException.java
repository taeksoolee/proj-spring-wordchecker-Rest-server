package com.wordchecker.exception;

// �ߺ�ȸ���� ���� ��� �߻��ϴ� ����
public class DuplicateMemberException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DuplicateMemberException() {
		super("�ߺ��� ȸ���� ���� �մϴ�.");
	}
}
