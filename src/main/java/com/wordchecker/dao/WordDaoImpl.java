package com.wordchecker.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordchecker.dto.Word;
import com.wordchecker.dto.WordFilter;
import com.wordchecker.dto.WordTestFilter;
import com.wordchecker.mapper.WordMapper;

@Repository
public class WordDaoImpl implements WordDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Word> selectWordList(WordFilter filter) {
		return sqlSession.getMapper(WordMapper.class).selectWordList(filter);
	}
	
	@Override
	public List<Word> selectWordTest(WordTestFilter filter) {
		return sqlSession.getMapper(WordMapper.class).selectWordTest(filter);
	}

	@Override
	public Word selectWordNo(int no) {
		return sqlSession.getMapper(WordMapper.class).selectWrodNo(no);
	}

	@Override
	public int insertWord(List<Word> wordList) {
		return sqlSession.getMapper(WordMapper.class).insertWord(wordList);
	}

	@Override
	public int updateWord(Word word) {
		return sqlSession.getMapper(WordMapper.class).updateWord(word);
	}

	@Override
	public int updateWordState(Word word) {
		return sqlSession.getMapper(WordMapper.class).updateWordState(word);
	}
}
