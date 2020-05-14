package com.wordchecker.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordchecker.dto.Member;
import com.wordchecker.exception.DuplicateMemberException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.NotAuthException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;
import com.wordchecker.service.MemberService;
import com.wordchecker.util.JwtManager;

import io.jsonwebtoken.Claims;

@RestController
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtManager jwtManager;
	
	@RequestMapping(value="/auth/member", method=RequestMethod.GET)
	public Member getMember(HttpServletRequest request) throws MemberNotFoundException{
		Member member = new Member();
		int no = jwtManager.getJwtValueToRequestAttribute(request);
		member.setNo(no);
		
		Member searchMember = memberService.getMemberMember(member);
		return searchMember;
	}
	
	@RequestMapping(value="/member/search/email", method=RequestMethod.POST)
	public Map<String, Object> getMemberSearchEmail(@RequestBody Member member){
		Member searchMember = memberService.getMemberMember(member);
	    String email = searchMember==null?"":searchMember.getEmail();
	    
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", email);
		
		return result;
	}
	
	@RequestMapping(value="/member/search/nickname", method=RequestMethod.POST)
	public Map<String, Object> getMemberSearchNickname(@RequestBody Member member){
		Member searchMember = memberService.getMemberMember(member);
	    String nickname = searchMember==null?"":searchMember.getNickname();
	    
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("nickname", nickname);
		
		return result;
	}
	
	@RequestMapping(value="/member/search/password", method=RequestMethod.POST)
	public Map<String, Object> getMemberSearchPassword(@RequestBody Member member) throws MemberNotFoundException, MessagingException {
		int resultRow = memberService.modifyMemberSearchPassword(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", resultRow);
		result.put("message", "임시비밀번호 발송");
		
		return result;
	}
	
	@RequestMapping(value="/member/login/{autoLogin}", method=RequestMethod.POST)
	public Map<String, Object> memberLogin(@RequestBody Member member, @PathVariable boolean autoLogin) throws WrongAccessException, MemberNotFoundException, UnsupportedEncodingException{
		Member loginMember = memberService.getLogin(member);
		String jwt = "";
		if(autoLogin) {
			jwt =jwtManager.getJwt(System.currentTimeMillis()+(24*60*60*1000), loginMember);
		}else {
			jwt =jwtManager.getJwt(loginMember);
		}
		
		long time = jwtManager.convertJwtToClaim(jwt).getExpiration().getTime();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "로그인 성공");
		result.put("jwt", jwt);
		result.put("time", time);
		
		return result;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/auth/jwt/refresh/{minute}", method=RequestMethod.GET)
	public Map<String, Object> refreshJwt(@PathVariable int minute, HttpServletRequest request) throws UnsupportedEncodingException{
		String jwt = request.getHeader("jwt");
		Claims claim = jwtManager.convertJwtToClaim(jwt);
		Date exp = claim.getExpiration();
		exp.setMinutes(exp.getMinutes()+minute);
		
		int no = (int) claim.get("no");
		Member member = new Member();
		member.setNo(no);
		member = memberService.getMemberMember(member);
		
		jwt = jwtManager.getJwt(exp.getTime(), member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "jwt 갱신");
		result.put("jwt", jwt);
		result.put("time", exp.getTime());
		
		return result;
	}
	
	@RequestMapping(value="/member/check", method=RequestMethod.POST)
	public Map<String, Object> membercCheck(HttpServletRequest request) throws WrongAccessException, MemberNotFoundException, UnsupportedEncodingException, NotAuthException{
		String jwt = request.getHeader("jwt");
		int no = 0;
		Claims claim = null;
		
		if(jwt != null) {
			claim = jwtManager.convertJwtToClaim(jwt);
			if(claim == null) throw new NotAuthException();
			no = (int) claim.get("no");
		}
		
		long time = claim.getExpiration().getTime();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("memberNo", no);
		result.put("time", time);
		
		return result;
	}
	
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public Map<String, Object> addMember(@RequestBody Member member) throws DuplicateMemberException, InvalidException, XssException{
		int resultRow = memberService.addMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원가입 완료");
		
		return result;
	}
	
	@RequestMapping(value="/auth/member", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMember(@RequestBody Member member, HttpServletRequest request) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException{
		int no = jwtManager.getJwtValueToRequestAttribute(request);
		member.setNo(no);
		
		int resultRow = memberService.modifyMember(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원정보 수정완료");
		
		return result;
	}
	
	@RequestMapping(value="/auth/member/state", method=RequestMethod.PATCH)
	public Map<String, Object> modifyMemberState(@RequestBody Member member, HttpServletRequest request) throws DuplicateMemberException, InvalidException, WrongAccessException, MemberNotFoundException{
		int no = jwtManager.getJwtValueToRequestAttribute(request);
		member.setNo(no);
		
		int resultRow = memberService.modifyMemberState(member);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "회원상태 수정완료");
		
		return result;
	}
}