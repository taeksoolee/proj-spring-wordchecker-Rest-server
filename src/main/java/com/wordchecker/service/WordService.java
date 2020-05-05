package com.wordchecker.service;

import java.util.List;

import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;

public interface WordService {
	List<Word> getWord(WordFilter filter);
	Word getWrodNo(int no);
	int addWord(Word word);
	int modifyWord(Word word);
	int modifyWordState(Word word);
}
