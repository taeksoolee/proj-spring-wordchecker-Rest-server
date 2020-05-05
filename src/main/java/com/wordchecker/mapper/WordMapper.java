package com.wordchecker.mapper;

import java.util.List;

import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;

public interface WordMapper {
	List<Word> selectWord(WordFilter filter);
	Word selectWrodNo(int no);
	int insertWord(Word word);
	int updateWord(Word word);
	int updateWordState(Word word);
}
