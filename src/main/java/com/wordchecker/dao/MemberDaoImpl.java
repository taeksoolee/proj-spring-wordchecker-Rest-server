package com.wordchecker.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordchecker.dto.Member;
import com.wordchecker.mapper.MemberMapper;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Member selectMemberMember(Member member) {
		return sqlSession.getMapper(MemberMapper.class).selectMemberMember(member);
	}

	@Override
	public int insertMember(Member member) {
		return sqlSession.getMapper(MemberMapper.class).insertMember(member);
	}

	@Override
	public int updateMember(Member member) {
		return sqlSession.getMapper(MemberMapper.class).updateMember(member);
	}

	@Override
	public int updateMemberState(Member member) {
		return sqlSession.getMapper(MemberMapper.class).updateMember(member);
	}

	@Override
	public int updateMemberLastLogin(int no) {
		return sqlSession.getMapper(MemberMapper.class).updateMemberLastLogin(no);
	}

}