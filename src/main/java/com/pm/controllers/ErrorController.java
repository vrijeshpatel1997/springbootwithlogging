package com.pm.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class ErrorController {
	
	private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

	//@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		logger.warn("handleError, STARTED ");
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				logger.error("handleError, ERROR Occured:{} ", "Page Not FOUND");
				return "error404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				logger.error("handleError, ERROR Occured:{} ", "Internal Server Error");
				return "error500";
			}
			else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				logger.error("handleError, ERROR Occured:{} ", "Access Denied");
				return "error403";
			}
		}
		logger.warn("handleError, FINIHSED ");
		return "error";
	}
	
	public String getErrorPath() {
		return "/error";
	}

}
