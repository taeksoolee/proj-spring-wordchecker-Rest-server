package com.wordchecker.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wordchecker.exception.DuplicateMemberException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;
import com.wordchecker.service.MemberService;
import com.wordchecker.util.JwtManager;
import com.wordchecker.util.MailManager;

@Controller
@ResponseBody
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtManager jwtManager;
	
	@RequestMapping(value="/member/search/email", method=RequestMethod.GET)
	public Map<String, Object> getMemberSearchEmail(@ModelAttribute Member member) throws MemberNotFoundException {
	    String email = memberService.getMemberMember(member).getEmail();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", email);
		
		return result;
	}
	
	@RequestMapping(value="/member/search/password", method=RequestMethod.GET)
	public Map<String, Object> getMemberSearchPassword(@ModelAttribute Member member) throws MemberNotFoundException, MessagingException {
		int resultRow = memberService.modifyMemberSearchPassword(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", resultRow);
		result.put("message", "임시 비밀번호를 발송하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public Map<String, Object> getMemberLogin(@RequestBody Member member, HttpServletResponse response) throws WrongAccessException, MemberNotFoundException, UnsupportedEncodingException{
		Member loginMember = memberService.getLogin(member, response);
		
		String jwt =jwtManager.getJwt(loginMember);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "로그인에 성공하였습니다.");
		result.put("jwt", jwt);
		
		return result;
	}
	
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public Map<String, Object> addMember(@RequestBody Member member) throws DuplicateMemberException, InvalidException, XssException{
		int resultRow = memberService.addMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원가입에 성공하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/member", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMember(@RequestBody Member member, HttpServletRequest request) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException{
		int no = jwtManager.getJwtValueToRequestAttribute(request);
		member.setNo(no);
		
		int resultRow = memberService.modifyMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원정보를 수정하였습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/member/state", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMemberState(@RequestBody Member member, HttpServletRequest request) throws DuplicateMemberException, InvalidException, WrongAccessException, MemberNotFoundException{
		int no = jwtManager.getJwtValueToRequestAttribute(request);
		member.setNo(no);
		
		int resultRow = memberService.modifyMemberState(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원상태를 수정하였습니다.");
		
		return result;
	}
}
