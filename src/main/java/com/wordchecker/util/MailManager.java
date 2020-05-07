package com.wordchecker.util;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@PropertySource("/WEB-INF/conf/site.properties")
public class MailManager {
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Value("${site.loginpage}")
	private String loginPage;
	
	public String sendHtmlEmail(String email, String subject, String content) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		message.setSubject(subject);
		message.setText(content, "utf-8", "html");
		message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
		
		mailSender.send(message);
		return email;
	}
	
	public String decorateHtmlPassword(String password) {
		/*
		<div style='text-align: center; border: 1px solid lightgray; border-radius: 5px; margin: 25px auto; width: 800px; background-color: lightgoldenrodyellow;'>
	    	<p style='font-size: 20px;'>word-checker �ӽ� ��й�ȣ �ȳ�</p>
	    	<p style='font-size: 30px; font-weight: bold;'>password-value</p>
	    	<p style='color:red;'>�ݵ�� ��й�ȣ�� �ٽ� �������ּ���.</p>
	    	<p><a href='conf-loginpage-url'>word-checker �ٷΰ���</a></p>
		</div>
		 */
		StringBuffer html = new StringBuffer();
		html.append("<div style='text-align: center; border: 1px solid lightgray; border-radius: 5px; margin: 25px auto; width: 800px; background-color: lightgoldenrodyellow;'>");
			html.append("<p style='font-size: 20px;'>word-checker �ӽ� ��й�ȣ �ȳ�</p>");
			html.append("<p style='font-size: 30px; font-weight: bold;'>" + password + "</p>");
			html.append("<p style='color:red;'>�ݵ�� ��й�ȣ�� �ٽ� �������ּ���.</p>");
			html.append("<p><a href='" + loginPage + "'>word-checker �ٷΰ���</a></p>");
		html.append("</div>");
		return html.toString();
	}
}
