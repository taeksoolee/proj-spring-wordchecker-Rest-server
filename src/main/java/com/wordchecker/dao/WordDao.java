package com.wordchecker.dao;

import java.util.List;

import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;
import com.wordchecker.dto.WordTestFilter;

public interface WordDao {
	List<Word> selectWord(WordFilter filter);
	List<Word> selectWordTest(WordTestFilter filter);
	Word selectWrodNo(int no);
	int insertWord(Word word);
	int updateWord(Word word);
	int updateWordState(Word word);
}
