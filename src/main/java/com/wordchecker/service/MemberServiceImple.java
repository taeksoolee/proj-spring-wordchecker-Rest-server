package com.wordchecker.service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wordchecker.dao.MemberDao;
import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateMemberException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;
import com.wordchecker.util.Encryption;
import com.wordchecker.util.JwtManager;
import com.wordchecker.util.MailManager;
import com.wordchecker.util.Validation;

@Service
public class MemberServiceImple implements MemberService{
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private Validation validation;
	@Autowired
	private MailManager mailManger;

	@Override
	public Member getMemberMember(Member member) {
		return memberDao.selectMemberMember(member);
	}
	
	@Override
	@Transactional
	public Member getLogin(Member member, HttpServletResponse response) throws WrongAccessException, MemberNotFoundException, UnsupportedEncodingException {
		if(!(member.getEmail() != null && member.getPassword() != null)) throw new WrongAccessException();
		
		Member loginMember = memberDao.selectMemberMember(Encryption.encrptMemberPassword(member));
		if(loginMember == null) throw new MemberNotFoundException();
		memberDao.updateMemberLastLogin(loginMember.getNo());
		
		return loginMember;
	}
	
	@Override
	@Transactional
	public int modifyMemberSearchPassword(Member member) throws MemberNotFoundException, MessagingException{
		Member selectMember = memberDao.selectMemberMember(member);
		if(selectMember == null) throw new MemberNotFoundException();
		
		String randomPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		
		mailManger.sendHtmlEmail(member.getEmail(), "[word-checker]비밀번호 안내", mailManger.decorateHtmlPassword(randomPassword));
		
		Member updateMember = new Member();
		updateMember.setNo(selectMember.getNo());
		updateMember.setPassword(randomPassword);
		
		updateMember = Encryption.encrptMemberPassword(updateMember);
		return memberDao.updateMember(updateMember);
	}
	
	@Override
	@Transactional
	public int addMember(Member member) throws DuplicateMemberException, InvalidException, XssException {
		validation.validateMember(member);
		
		//중복회원 확인
		Member tempMember = new Member();
		tempMember.setEmail(member.getEmail());
		if(memberDao.selectMemberMember(tempMember) != null) throw new DuplicateMemberException();
		
		member = Encryption.encrptMemberPassword(member);
		return memberDao.insertMember(member);
	}

	@Override
	@Transactional
	public int modifyMember(Member member) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException {
		if(member.getNo() == 0) throw new WrongAccessException();
		
		validation.validateMember(member);
		
		if(!(member.getPassword() == null && member.getPassword().equals(""))) {
			Encryption.encrptMemberPassword(member);
		}
		
		return memberDao.updateMember(member);
	}
	

	@Override
	public int modifyMemberState(Member member) throws WrongAccessException {
		if(member.getNo() == 0) throw new WrongAccessException();
		
		return memberDao.updateMemberState(member);
	}
}