package com.wordchecker.dao;

import java.util.List;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;

public interface BoardDao {
	List<BoardMember> selectBoardMember(BoardFilter filter);
	Board selectBoardNo(int no);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int updateBoardState(Board board);
}
