package com.wordchecker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wordchecker.dao.BoardDao;
import com.wordchecker.dao.MemberDao;
import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;
import com.wordchecker.dto.Member;
import com.wordchecker.exception.MemberNotFoundException;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<BoardMember> getBoardMember(BoardFilter filter) {
		return boardDao.selectBoardMember(filter);
	}

	@Override
	public Board getBoardNo(int no) {
		return boardDao.selectBoardNo(no);
	}

	@Override
	public int addBoard(Board board) throws MemberNotFoundException {
		Member requestMember = new Member();
		requestMember.setNo(board.getMemberNo());
		if(memberDao.selectMemberMember(requestMember)==null) throw new MemberNotFoundException();
		
		return boardDao.insertBoard(board);
	}

	@Override
	public int modifyBoard(Board board) throws MemberNotFoundException {
		Member requestMember = new Member();
		requestMember.setNo(board.getMemberNo());
		if(memberDao.selectMemberMember(requestMember)==null) throw new MemberNotFoundException();
		
		return boardDao.updateBoard(board);
	}

	@Override
	public int modifyBoardState(Board board) throws MemberNotFoundException {
		Member requestMember = new Member();
		requestMember.setNo(board.getMemberNo());
		if(memberDao.selectMemberMember(requestMember)==null) throw new MemberNotFoundException();
		
		return boardDao.updateBoardState(board);
	}
}
