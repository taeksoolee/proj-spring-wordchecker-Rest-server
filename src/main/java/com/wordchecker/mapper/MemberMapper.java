package com.wordchecker.mapper;

import java.util.List;

import com.wordchecker.dto.Member;

public interface MemberMapper {
	List<Member> selectMember();
}
