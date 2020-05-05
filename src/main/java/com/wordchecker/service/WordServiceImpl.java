package com.wordchecker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wordchecker.dao.WordDao;
import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;

@Service
public class WordServiceImpl implements WordService{
	@Autowired
	private WordDao wordDao;
	
	@Override
	public List<Word> getWord(WordFilter filter) {
		return wordDao.selectWord(filter);
	}

	@Override
	public Word getWrodNo(int no) {
		return wordDao.selectWrodNo(no);
	}

	@Override
	public int addWord(Word word) {
		return wordDao.insertWord(word);
	}

	@Override
	public int modifyWord(Word word) {
		return wordDao.updateWord(word);
	}

	@Override
	public int modifyWordState(Word word) {
		return wordDao.updateWordState(word);
	}

}
