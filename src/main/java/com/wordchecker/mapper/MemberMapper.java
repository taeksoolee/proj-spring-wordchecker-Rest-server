package com.wordchecker.mapper;

import com.wordchecker.dto.Member;

public interface MemberMapper {
	Member selectMemberMember(Member member);
	int insertMember(Member member);
	int updateMember(Member member);
	int updateMemberState(Member member);
	int updateMemberLastLogin(int no);
}
