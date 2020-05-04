package com.wordchecker.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.wordchecker.dto.Member;

public interface MemberDao {
	List<Member> selectMember();
}
