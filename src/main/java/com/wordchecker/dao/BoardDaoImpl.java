package com.wordchecker.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;
import com.wordchecker.mapper.BoardMapper;

@Repository
public class BoardDaoImpl implements BoardDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardMember> selectBoardMember(BoardFilter filter) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardMember(filter);
	}

	@Override
	public Board selectBoardNo(int no) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardNo(no);
	}

	@Override
	public int insertBoard(Board board) {
		return sqlSession.getMapper(BoardMapper.class).insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return sqlSession.getMapper(BoardMapper.class).updateBoard(board);
	}

	@Override
	public int updateBoardState(Board board) {
		return sqlSession.getMapper(BoardMapper.class).updateBoardState(board);
	}
}