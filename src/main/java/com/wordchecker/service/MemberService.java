package com.wordchecker.service;

import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateEntityException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;

public interface MemberService {
	//Member getMemberNo(int no);
	//Member getMemberEmail(String email);
	Member getMemberMember(Member member);
	Member getLogin(Member member) throws WrongAccessException, MemberNotFoundException;
	int modifyMemberSearchPassword(Member member) throws MemberNotFoundException;
	int addMember(Member member) throws DuplicateEntityException, InvalidException;
	int modifyMember(Member member) throws InvalidException, WrongAccessException, MemberNotFoundException;
	int modifyMemberState(Member member) throws WrongAccessException;
	//int modifyMemberLastLogin(int no);
}
