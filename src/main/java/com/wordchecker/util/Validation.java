package com.wordchecker.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.Member;
import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordTestFilter;
import com.wordchecker.exception.DevMistakeException;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.XssException;

@Component
public class Validation {
	public static final String MEMBER_EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	// 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력
	public static final String MEMBER_PASSWORD_REGEX = "(?=.*\\d{1,50})(?=.*[~`!@#$%\\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$";
	// 1~10자리 문자
	public static final String MEMBER_NICKNAME_REGEX = "^[a-zA-Z~`!@#$%\\/\\/^&*()-+=0-9]{1,10}$";
	public static final String MEMBER_BIRTHDAY_REGEX = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	public static final String WORD_SPELING_REGEX = "^[a-zA-Z]{0,30}$";
	public static final String WORD_MEANING_REGEX = "^[가-힣]{0,30}$";
	public static final String BOARD_TITLE_REGEX = "^.{1,30}$";
	public static final String BOARD_CONTENT_REGEX = "^.{0,50}$";
	
	public static final String DATE_REGEX = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	
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
	
	public void validateWordTestFilter(WordTestFilter filter) throws InvalidException {
		boolean inValid = false;
		inValid = (!inValid && Pattern.matches(DATE_REGEX, filter.getStartDate()))?true:false;
		inValid = (!inValid && Pattern.matches(DATE_REGEX, filter.getEndDate()))?true:false;
		inValid = (!inValid && Pattern.matches(WORD_SPELING_REGEX, filter.getSpeling()))?true:false;
		inValid = (!inValid && Pattern.matches(WORD_MEANING_REGEX, filter.getMeaning()))?true:false;
		if(inValid) throw new InvalidException();
		
	}
}