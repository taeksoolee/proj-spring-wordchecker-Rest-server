package com.wordchecker.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordchecker.exception.MemberNotFoundException;
import com.wordchecker.exception.NotAuthException;

public class AuthFilter implements Filter{
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(((HttpServletRequest)request).getHeaderNames());
		String jwt = ((HttpServletRequest)request).getHeader("jwt");
		System.out.println(jwt);
		try {
			if(!(jwt != null && !jwt.equals(""))) throw new NotAuthException();
			request.setAttribute("jwt", jwt);
			chain.doFilter(request, response);
		} catch (NotAuthException e) {
			setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
		}
	}

	public void setErrorResponse(HttpStatus status, ServletResponse response, Exception exception) throws IOException{
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		servletResponse .setStatus(status.value());
		servletResponse .setContentType("application/json");
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", exception.getMessage());
        
        String json = mapper.writeValueAsString(map);
        response.getWriter().write(json);
    }
	
}
