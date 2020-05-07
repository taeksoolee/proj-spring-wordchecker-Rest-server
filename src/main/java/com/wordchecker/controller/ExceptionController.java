package com.wordchecker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ResponseBody
public class ExceptionController {
	private Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Map<String, Object> AjaxExceptionHandler(HttpServletResponse response, Exception e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", e.getMessage());
		return map;
	}
}
