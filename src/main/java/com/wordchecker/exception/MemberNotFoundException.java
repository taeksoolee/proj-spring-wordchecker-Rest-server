package com.wordchecker.exception;

//ȸ�� ������ ã�� �� ���� �� �߻��Ǵ� ����
public class MemberNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MemberNotFoundException() {
		super("��û�Ͻ� ȸ�� ������ ã�� �� �����ϴ�.");
	}
}
