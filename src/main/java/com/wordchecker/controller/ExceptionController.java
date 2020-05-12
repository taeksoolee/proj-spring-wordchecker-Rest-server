package com.wordchecker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
	@ResponseBody
	public Map<String, Object> ExceptionHandler(HttpServletResponse response, Exception e) {
		e.printStackTrace();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", e.getMessage());
		return map;
	}
}
