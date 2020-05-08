package mytest;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wordchecker.dao.MemberDao;
import com.wordchecker.dao.WordDao;
import com.wordchecker.dto.WordTestFilter;
import com.wordchecker.util.JwtManager;
import com.wordchecker.util.MailManager;
import com.wordchecker.util.WordOrderType;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})
public class TestClass {
	private static final Logger logger=LoggerFactory.getLogger(TestClass.class);
	
	@Autowired
	private WordDao wordDao;
	
	@Autowired
	private JwtManager jwtmanager;
	
	@Autowired
	private MailManager mailManager;
	
	@Test
	public void mail() throws MessagingException {
		/*
		System.out.println("===");
		mailManager.sendHtmlEmail("9119k@naver.com", "title", "content");
		System.out.println("===");
		*/
	}
	
	@Test
	public void wordTest() {
		WordTestFilter filter =new WordTestFilter();
		filter.setWordOrderType(WordOrderType.SPELING_ASC);
		//System.out.println(filter.getWordOrderType());
		System.out.println(wordDao.selectWordTest(filter));
	}
	
	
}
