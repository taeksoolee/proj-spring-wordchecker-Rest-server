package com.wordchecker.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.wordchecker.dto.Member;
import com.wordchecker.exception.MemberNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
@PropertySource("/WEB-INF/conf/jwt.properties")
public class JwtManager {
	@Value("${jwt.secretkey}")
	private String secretKey;
	
	@Value("${jwt.typ}")
	private String typ;
	
	@Value("${jwt.alg}")
	private String alg;
	
	@Value("${jwt.defaultExpMinute}")
	private String defaultExpMinute;
	
	public String getJwt(int minite, Member member) throws UnsupportedEncodingException {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date(System.currentTimeMillis() + (60*1000)*minite);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", typ);
        headerMap.put("alg", alg);

        Map<String, Object> map= new HashMap<String, Object>();

        map.put("no", member.getNo());

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
	}
	
	public String getJwt(Member member) throws UnsupportedEncodingException {
		System.out.println(alg + "|" + typ + "|" + defaultExpMinute + "|" + secretKey);
		return getJwt(Integer.parseInt(defaultExpMinute), member);
	}
	
	public Claims convertJwtToClaim(String jwt) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt).getBody();
        }catch (ExpiredJwtException exception) {
            exception.printStackTrace();
        } catch (JwtException exception) {
        	exception.printStackTrace();
        }
        	
		return claims;
	}
	
	public int getJwtValueToRequestAttribute(HttpServletRequest request) throws MemberNotFoundException {
		String jwt = (String) request.getAttribute("jwt");
		int no = convertJwtToClaim(jwt)!=null?(Integer)convertJwtToClaim(jwt).get("no"):0;
		
		if(no == 0) throw new MemberNotFoundException();
			
		return no;
	}

	@Override
	public String toString() {
		return "JwtManager [secretKey=" + secretKey + ", typ=" + typ + ", alg=" + alg + ", defaultExpMinute="
				+ defaultExpMinute + "]";
	}
}
