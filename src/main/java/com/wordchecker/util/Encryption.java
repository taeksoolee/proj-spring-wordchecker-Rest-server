package com.wordchecker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.wordchecker.dto.Board;
import com.wordchecker.dto.Member;
import com.wordchecker.dto.Word;
import com.wordchecker.exception.WrongAccessException;


public class Encryption {
	private static String encrypt(String source) {
		source = source + "soltString";
		StringBuffer password= new StringBuffer();
		
		try {
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			md.update(source.getBytes());
			
			byte[] digest=md.digest();
			
			for(int i=0;i<digest.length;i++) {
				password.append(Integer.toHexString(digest[i]&0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			
		}
		
		return password.toString();
	}
	
	public static Member encrptMemberPassword(Member member) {
		member.setPassword(encrypt(member.getPassword()));
		return member;
	}
}
