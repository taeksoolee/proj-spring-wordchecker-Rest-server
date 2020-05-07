package com.wordchecker.service;

import java.util.List;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;
import com.wordchecker.exception.MemberNotFoundException;

public interface BoardService {
	List<BoardMember> getBoardMember(BoardFilter filter);
	Board getBoardNo(int no);
	int addBoard(Board board) throws MemberNotFoundException;
	int modifyBoard(Board board) throws MemberNotFoundException;
	int modifyBoardState(Board board) throws MemberNotFoundException;
}
