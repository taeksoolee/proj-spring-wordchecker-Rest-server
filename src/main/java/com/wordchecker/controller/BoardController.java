package com.wordchecker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.BoardFilter;
import com.wordchecker.dto.BoardMember;
import com.wordchecker.dto.Word;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.service.BoardService;
import com.wordchecker.util.JwtManager;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private JwtManager jwtManager;
	
	private void setMemberNofromJwt(Board board, HttpServletRequest request) throws MemberNotFoundException {
		int memberNo = jwtManager.getJwtValueToRequestAttribute(request);
		board.setMemberNo(memberNo);
	}
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public List<BoardMember> getBoardMemberList(@ModelAttribute BoardFilter filter) {
		return boardService.getBoardMember(filter);
	}
	
	@RequestMapping(value="/auth/board/no/{no}", method=RequestMethod.GET)
	public Board getBoardNo(@PathVariable int no, HttpServletRequest request) throws MemberNotFoundException {
		jwtManager.getJwtValueToRequestAttribute(request);
		return boardService.getBoardNo(no);
	}
	
	@RequestMapping(value="/auth/board", method=RequestMethod.POST)
	public Map<String, Object> addBoard(@RequestBody Board board, HttpServletRequest request) throws MemberNotFoundException {
		setMemberNofromJwt(board, request);
		int resultRow = boardService.addBoard(board);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "게시물이 작성 되었습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/board", method=RequestMethod.PATCH)
	public Map<String, Object> modifyBoard(@RequestBody Board board, HttpServletRequest request) throws MemberNotFoundException {
		setMemberNofromJwt(board, request);
		
		int resultRow = boardService.modifyBoard(board);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "게시물이 작성 되었습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/board/state", method=RequestMethod.PATCH)
	public Map<String, Object> modifyBoardState(@RequestBody Board board, HttpServletRequest request) throws MemberNotFoundException {
		setMemberNofromJwt(board, request);
		
		int resultRow = boardService.modifyBoardState(board);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		switch (board.getState()) {
		case 0:
			result.put("message", "게시물이 비활성화 되었습니다.");
			break;
		case 1:
			result.put("message", "게시물이 활성화 되었습니다.");
			break;
		}
		return result;
	}
}