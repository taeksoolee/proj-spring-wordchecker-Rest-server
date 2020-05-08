package mytest;

import com.wordchecker.dto.WordTestFilter;
import com.wordchecker.util.WordOrderType;

public class AppTest {
	public static void main(String[] args) {
		WordTestFilter f = new WordTestFilter();
		f.setWordOrderType(WordOrderType.MEANING_DESC);
		
		System.out.println(f);
		
	}
	
}
