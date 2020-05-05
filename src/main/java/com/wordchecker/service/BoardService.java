package com.wordchecker.service;

import java.util.List;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;

public interface BoardService {
	List<BoardMember> getBoardMember(BoardFilter filter);
	Board getBoardNo(int no);
	int addBoard(Board board);
	int modifyBoard(Board board);
	int modifyBoardState(Board board);
}
