package com.wordchecker.mapper;

import java.util.List;

import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;
import com.wordchecker.dto.WordTestFilter;

public interface WordMapper {
	List<Word> selectWord(WordFilter filter);
	List<Word> selectWordTest(WordTestFilter filter);
	Word selectWrodNo(int no);
	int insertWord(List<Word> wordList);
	int updateWord(Word word);
	int updateWordState(Word word);
}
