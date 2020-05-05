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
		if(!(member.getEmail() != null && member.getPassword() != null)) throw new WrongAccessException("�߸��� �����Դϴ�.");
		
		Member loginMember = memberDao.selectMemberMember(member);
		if(loginMember == null) throw new MemberNotFoundException("��ġ�ϴ� ȸ�������� �������� �ʽ��ϴ�.");
		
		//jwt ����
		//****************************************/
				
		memberDao.updateMemberLastLogin(member.getNo());
		
		// ���� jwt ��ȯ
		return new Member();
	}
	
	@Override
	@Transactional
	public int modifyMemberSearchPassword(Member member) throws MemberNotFoundException{
		Member selectMember = memberDao.selectMemberMember(member);
		if(selectMember == null) throw new MemberNotFoundException("ȸ���� �߰ߵ��� �ʾҽ��ϴ�.");
		
		//������й�ȣ ����
		String tempPassword = UUID.randomUUID().toString().substring(0, 8);
		//���Ϲ߼�
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
		
		//�ߺ�ȸ�� Ȯ��
		Member tempMember = new Member();
		tempMember.setNo(member.getNo());
		if(memberDao.selectMemberMember(tempMember) != null) throw new DuplicateEntityException("�ߺ��� ȸ���� ���� �մϴ�.");
		
		return memberDao.insertMember(member);
	}

	@Override
	@Transactional
	public int modifyMember(Member member) throws InvalidException, WrongAccessException, MemberNotFoundException {
		if(member.getNo() == 0) throw new WrongAccessException("�߸��� ���� �Դϴ�.");
		
		//ȸ�� �������� Ȯ��
		Member tempMember = new Member();
		tempMember.setNo(member.getNo());
		if(memberDao.selectMemberMember(tempMember) == null) throw new MemberNotFoundException("��û�Ͻ� ȸ�� ������ ã�� �� �����ϴ�.");
		
		if(!validateMember(member)) throw new InvalidException("");
		
		return memberDao.updateMember(member);
	}
	

	@Override
	public int modifyMemberState(Member member) throws WrongAccessException {
		if(member.getNo() == 0) throw new WrongAccessException("�߸��� ���� �Դϴ�.");
		
		return memberDao.updateMemberState(member);
	}

/* ������� ����
	@Override
	public int modifyMemberLastLogin(int no) {
		return memberDao.updateMemberLastLogin(no);
	}
*/
	
	private boolean validateMember(Member member)  {
		//��ȿ�� �˻� ����
		
		return true;
		
	}

	



	
}