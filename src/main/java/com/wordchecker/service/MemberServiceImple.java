package com.wordchecker.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wordchecker.dao.MemberDao;
import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateEntityException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;

@Service
public class MemberServiceImple implements MemberService{
	@Autowired
	private MemberDao memberDao;
	/* 
	@Override
	public Member getMemberNo(int no) {
		return memberDao.selectMemberNo(no);
	}

	@Override
	public Member getMemberEmail(String email) {
		return memberDao.selectMemberEmail(email);
	}
	*/

	@Override
	public Member getMemberMember(Member member) {
		return memberDao.selectMemberMember(member);
	}
	
	@Override
	public Member getLogin(Member member) throws WrongAccessException, MemberNotFoundException {
		if(!(member.getEmail() != null && member.getPassword() != null)) throw new WrongAccessException("잘못된 접근입니다.");
		
		Member loginMember = memberDao.selectMemberMember(member);
		if(loginMember == null) throw new MemberNotFoundException("일치하는 회원정보가 존재하지 않습니다.");
		
		//jwt 발행
		//****************************************/
				
		memberDao.updateMemberLastLogin(member.getNo());
		
		// 발행 jwt 반환
		return new Member();
	}
	
	@Override
	@Transactional
	public int modifyMemberSearchPassword(Member member) throws MemberNotFoundException{
		Member selectMember = memberDao.selectMemberMember(member);
		if(selectMember == null) throw new MemberNotFoundException("회원이 발견되지 않았습니다.");
		
		//랜덤비밀번호 생성
		String tempPassword = UUID.randomUUID().toString().substring(0, 8);
		//메일발송
		//***********************/
		
		Member updateMember = new Member();
		updateMember.setNo(selectMember.getNo());
		updateMember.setPassword(tempPassword);
		
		return memberDao.updateMember(updateMember);
	}
	
	@Override
	@Transactional
	public int addMember(Member member) throws DuplicateEntityException, InvalidException {
		if(!validateMember(member)) throw new InvalidException("");
		
		//중복회원 확인
		Member tempMember = new Member();
		tempMember.setNo(member.getNo());
		if(memberDao.selectMemberMember(tempMember) != null) throw new DuplicateEntityException("중복된 회원이 존재 합니다.");
		
		return memberDao.insertMember(member);
	}

	@Override
	@Transactional
	public int modifyMember(Member member) throws InvalidException, WrongAccessException, MemberNotFoundException {
		if(member.getNo() == 0) throw new WrongAccessException("잘못된 접근 입니다.");
		
		//회원 존재유무 확인
		Member tempMember = new Member();
		tempMember.setNo(member.getNo());
		if(memberDao.selectMemberMember(tempMember) == null) throw new MemberNotFoundException("요청하신 회원 정보를 찾을 수 없습니다.");
		
		if(!validateMember(member)) throw new InvalidException("");
		
		return memberDao.updateMember(member);
	}
	

	@Override
	public int modifyMemberState(Member member) throws WrongAccessException {
		if(member.getNo() == 0) throw new WrongAccessException("잘못된 접근 입니다.");
		
		return memberDao.updateMemberState(member);
	}

/* 사용하지 않음
	@Override
	public int modifyMemberLastLogin(int no) {
		return memberDao.updateMemberLastLogin(no);
	}
*/
	
	private boolean validateMember(Member member)  {
		//유효성 검사 로직
		
		return true;
		
	}

	



	
}