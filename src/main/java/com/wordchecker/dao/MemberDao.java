package com.wordchecker.dao;

import com.wordchecker.dto.Member;

public interface MemberDao {
	Member selectMemberMember(Member member);
	int insertMember(Member member);
	int updateMember(Member member);
	int updateMemberState(Member member);
	int updateMemberLastLogin(int no);
}
