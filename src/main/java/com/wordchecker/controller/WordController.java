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

import com.wordchecker.dto.Member;
import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;
import com.wordchecker.service.MemberService;
import com.wordchecker.service.WordService;
import com.wordchecker.util.JwtManager;
import com.wordchecker.util.Validation;

@Controller
@ResponseBody
public class WordController {
	@Autowired
	private WordService wordService;
	@Autowired
	private JwtManager jwtManager;
	
	private void setMemberNofromJwt(Word word, HttpServletRequest request) throws MemberNotFoundException {
		int memberNo = jwtManager.getJwtValueToRequestAttribute(request);
		word.setMemberNo(memberNo);
	}
	
	@RequestMapping(value="/auth/word", method=RequestMethod.GET)
	public List<Word> getWordList(@ModelAttribute WordFilter filter, HttpServletRequest request) throws WrongAccessException, MemberNotFoundException{
		int memberNo = jwtManager.getJwtValueToRequestAttribute(request);
		filter.setMemberNo(memberNo);
		
		return wordService.getWord(filter);
	}
	
	@RequestMapping(value="/auth/word", method=RequestMethod.POST)
	public Map<String, Object> addWord(@RequestBody Word word, HttpServletRequest request) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException{
		setMemberNofromJwt(word, request);
		if(word.getMeaning() == null && word.getSpeling() == null) throw new WrongAccessException();
		int resultRow = wordService.addWord(word);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "단어 등록이 완료되었습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/word", method=RequestMethod.PATCH)
	public Map<String, Object> modifyWord(@RequestBody Word word, HttpServletRequest request) throws InvalidException, WrongAccessException, MemberNotFoundException, XssException{
		setMemberNofromJwt(word, request);
		if(!(word.getMeaning() != null && word.getSpeling() != null)) throw new WrongAccessException();
		int resultRow = wordService.modifyWord(word);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		result.put("message", "단어 수정이 완료되었습니다.");
		
		return result;
	}
	
	@RequestMapping(value="/auth/word/state", method=RequestMethod.PATCH)
	public Map<String, Object> modifyWordState(@RequestBody Word word, HttpServletRequest request) throws InvalidException, MemberNotFoundException, WrongAccessException, XssException{
		setMemberNofromJwt(word, request);
		int resultRow = wordService.modifyWordState(word);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", resultRow);
		switch(word.getState()) {
			case 0:
				result.put("message", "단어 비활성화이 완료되었습니다.");
				break;
			case 1:
				result.put("message", "단어 활성화이 완료되었습니다.");
				break;
		}
		
		return result;
	}
}