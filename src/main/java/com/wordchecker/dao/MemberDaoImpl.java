package com.wordchecker.dao;

import java.util.List;

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
	public List<Member> selectMember() {
		return sqlSession.getMapper(MemberMapper.class).selectMember();
	}
	
	
	
	
}
