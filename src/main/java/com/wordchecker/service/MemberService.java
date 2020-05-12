package com.wordchecker.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateMemberException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;

public interface MemberService {
	Member getMemberMember(Member member);
	Member getLogin(Member member) throws WrongAccessException, MemberNotFoundException, UnsupportedEncodingException;
	int modifyMemberSearchPassword(Member member) throws MemberNotFoundException, MessagingException;
	int addMember(Member member) throws DuplicateMemberException, InvalidException, XssException;
	int modifyMember(Member member) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException;
	int modifyMemberState(Member member) throws WrongAccessException;
}
