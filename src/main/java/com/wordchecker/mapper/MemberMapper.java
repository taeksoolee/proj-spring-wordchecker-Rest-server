package com.wordchecker.mapper;

import java.util.List;

import com.wordchecker.dto.Member;

public interface MemberMapper {
	//Member selectMemberNo(int no);
	//Member selectMemberEmail(String email);
	Member selectMemberMember(Member member);
	int insertMember(Member member);
	int updateMember(Member member);
	int updateMemberState(Member member);
	int updateMemberLastLogin(int no);
}
