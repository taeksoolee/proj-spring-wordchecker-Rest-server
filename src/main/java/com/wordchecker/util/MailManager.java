package com.wordchecker.util;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@PropertySource("/WEB-INF/conf/site.properties")
public class MailManager {
	private Logger logger = LoggerFactory.getLogger(MailManager.class);
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${site.loginpage}")
	private String loginPage;
	
	public String sendHtmlEmail(String email, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setFrom(new InternetAddress(username));
			message.setSubject(subject);
			message.setText(content, "utf-8", "html");
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
		return email;
	}
	
	public String decorateHtmlPassword(String password) {
		/*
		<div style='text-align: center; border: 1px solid lightgray; border-radius: 5px; margin: 25px auto; width: 800px; background-color: lightgoldenrodyellow;'>
	    	<p style='font-size: 20px;'>word-checker 임시 비밀번호 안내</p>
	    	<p style='font-size: 30px; font-weight: bold;'>password-value</p>
	    	<p style='color:red;'>반드시 비밀번호를 다시 변경해주세요.</p>
	    	<p><a href='conf-loginpage-url'>word-checker 바로가기</a></p>
		</div>
		 */
		StringBuffer html = new StringBuffer();
		html.append("<div style='text-align: center; border: 1px solid lightgray; border-radius: 5px; margin: 25px auto; width: 800px; background-color: lightgoldenrodyellow;'>");
			html.append("<p style='font-size: 20px;'>word-checker 임시 비밀번호 안내</p>");
			html.append("<p style='font-size: 30px; font-weight: bold;'>" + password + "</p>");
			html.append("<p style='color:red;'>반드시 비밀번호를 다시 변경해주세요.</p>");
			html.append("<p><a href='" + loginPage + "'>word-checker 바로가기</a></p>");
		html.append("</div>");
		return html.toString();
	}
}
