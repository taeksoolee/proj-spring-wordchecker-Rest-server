package com.wordchecker.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.Member;
import com.wordchecker.dto.Word;
import com.wordchecker.exception.DevMistakeException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.XssException;

@Component
public class Validation {
	
	public static final String MEMBER_EMAIL_REGEX = "";
	public static final String MEMBER_PASSWORD_REGEX = "";
	public static final String MEMBER_NICKNAME_REGEX = "";
	public static final String MEMBER_BIRTHDAY_REGEX = "";
	public static final String WORD_SPELING_REGEX = "";
	public static final String WORD_MEANING_REGEX = "";
	public static final String BOARD_TITLE_REGEX = "";
	public static final String BOARD_CONTENT_REGEX = "";
	
	private void rejectXss(String string) throws XssException{
		if(string.split("[<>]").length != 1) throw new XssException();
	}
	
	private void rejectXss(Member member) throws XssException {
		rejectXss(member.getEmail());
		rejectXss(member.getNickname());
	}
	private void rejectXss(Word word) throws XssException {
		rejectXss(word.getSpeling());
		rejectXss(word.getMeaning());
	}
	private void rejectXss(Board board) throws XssException {
		rejectXss(board.getTitle());
		rejectXss(board.getContent());
	}
	
	/** 이 클래스 내 상수를 이용한는 것을 권장함 */
	public void testRegExp(String regex, String input) throws InvalidException{
		if(!Pattern.matches(regex, input)) throw new InvalidException();
	}
	
	public void testRegExp(Map<String, List<String>> map, Logger logger) throws InvalidException{
		List<String> regexList = map.get("regexList"); 
		List<String> inputList = map.get("inputList"); 
		try {
			if(regexList.size() == inputList.size()) throw new DevMistakeException("맵 형식이 일치하지 않습니다.(map에는 regexList와 inputList 객체가 존재해야 합니다)");
		} catch (DevMistakeException e) {
			logger.error(e.getMessage());
		}
		
		boolean inValid = false;
		for(int i = 0; i<inputList.size()-1; i++) {
			inValid = (!inValid && Pattern.matches(regexList.get(i), inputList.get(i)))?true:false;
		}
		
		if(inValid) throw new InvalidException();
	}
	
	public void validateMember(Member member) throws InvalidException, XssException {
		rejectXss(member);
		
		boolean inValid = false;
		inValid = (!inValid && Pattern.matches(MEMBER_EMAIL_REGEX, member.getEmail()))?true:false;
		inValid = (!inValid && Pattern.matches(MEMBER_PASSWORD_REGEX, member.getPassword()))?true:false;
		inValid = (!inValid && Pattern.matches(MEMBER_NICKNAME_REGEX, member.getNickname()))?true:false;
		inValid = (!inValid && Pattern.matches(MEMBER_BIRTHDAY_REGEX, member.getBirthday()))?true:false;
		if(inValid) throw new InvalidException();
	}
	
	public void validateWord(Word word) throws InvalidException, XssException {
		rejectXss(word);
		
		boolean inValid = false;
		inValid = (!inValid && Pattern.matches(WORD_SPELING_REGEX, word.getSpeling()))?true:false;
		inValid = (!inValid && Pattern.matches(WORD_MEANING_REGEX, word.getMeaning()))?true:false;
		if(inValid) throw new InvalidException();
	}
	
	public void validateBoard(Board board) throws InvalidException, XssException {
		rejectXss(board);
		
		boolean inValid = false;
		inValid = (!inValid && Pattern.matches(BOARD_TITLE_REGEX, board.getTitle()))?true:false;
		inValid = (!inValid && Pattern.matches(BOARD_CONTENT_REGEX, board.getContent()))?true:false;
		if(inValid) throw new InvalidException();
	}
}