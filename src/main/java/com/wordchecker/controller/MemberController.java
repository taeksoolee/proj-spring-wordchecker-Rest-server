package com.wordchecker.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateEntityException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.service.MemberService;

@Controller
@ResponseBody
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/member", method=RequestMethod.GET)
	public Member getMemberMember(@ModelAttribute Member member) {
		return memberService.getMemberMember(member);
	}
	
	@RequestMapping(value="/member/search/password", method=RequestMethod.GET)
	public Map<String, Object> getMemberSearchPassword(@ModelAttribute Member member) throws MemberNotFoundException {
		int resultRow = memberService.modifyMemberSearchPassword(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "임시 비밀번호를 발송하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public Map<String, Object> getMemberLogin(@RequestBody Member member) throws WrongAccessException, MemberNotFoundException{
		//jwt 생성
		Member jwt = memberService.getLogin(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "로그인에 성공하였습니다.");
		result.put("jwt", jwt);
		
		return result;
	}
	
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public Map<String, Object> addMember(@RequestBody Member member) throws DuplicateEntityException, InvalidException{
		int resultRow = memberService.addMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원가입에 성공하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/member", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMember(@RequestBody Member member) throws InvalidException, WrongAccessException, MemberNotFoundException{
		int resultRow = memberService.modifyMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원정보를 수정하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/member/state", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMemberState(@RequestBody Member member) throws DuplicateEntityException, InvalidException, WrongAccessException{
		int resultRow = memberService.modifyMemberState(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원상태를 수정하였습니다.");
		
		return result;
	}
	
	
	
}
