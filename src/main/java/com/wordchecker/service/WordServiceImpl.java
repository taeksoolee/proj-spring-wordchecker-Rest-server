package com.wordchecker.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wordchecker.dao.MemberDao;
import com.wordchecker.dao.WordDao;
import com.wordchecker.dto.Member;
import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;
import com.wordchecker.dto.WordTestFilter;
import com.wordchecker.exception.InvalidException;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.WrongAccessException;
import com.wordchecker.exception.XssException;
import com.wordchecker.util.JwtManager;
import com.wordchecker.util.Validation;

@Service
public class WordServiceImpl implements WordService{
	@Autowired
	private WordDao wordDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Validation validation;
	
	@Override
	public List<Word> getWordList(WordFilter filter) {
		return wordDao.selectWordList(filter);
	}

	@Override
	public List<Word> getWordTest(WordTestFilter filter) throws InvalidException {
		validation.validateWordTestFilter(filter);
		return wordDao.selectWordTest(filter);
	}
	
	@Override
	public Word getWordNo(int no) {
		return wordDao.selectWordNo(no);
	}

	@Override
	@Transactional
	public int addWord(List<Word> wordList) throws MemberNotFoundException, InvalidException, XssException {
		for(Word word : wordList) {
			validation.validateWord(word);
		}
		
		Member requestMember = new Member();
		for(Word word : wordList) {
			requestMember.setNo(word.getMemberNo());
		}
		if(memberDao.selectMemberMember(requestMember) == null) throw new MemberNotFoundException();
		
		return wordDao.insertWord(wordList);
	}

	@Override
	@Transactional
	public int modifyWord(Word word) throws MemberNotFoundException, WrongAccessException, InvalidException, XssException {
		if(wordDao.selectWordNo(word.getNo()) == null) throw new WrongAccessException();
		
		validation.validateWord(word);
		
		Member requestMember = new Member();
		requestMember.setNo(word.getMemberNo());
		if(memberDao.selectMemberMember(requestMember) == null) throw new MemberNotFoundException();
		
		
		return wordDao.updateWord(word);
	}

	@Override
	@Transactional
	public int modifyWordState(Word word) throws MemberNotFoundException, WrongAccessException, InvalidException, XssException {
		if(wordDao.selectWordNo(word.getNo()) == null) throw new WrongAccessException();
		
		Member requestMember = new Member();
		requestMember.setNo(word.getMemberNo());
		if(memberDao.selectMemberMember(requestMember) == null) throw new MemberNotFoundException();
		
		return wordDao.updateWordState(word);
	}

}
