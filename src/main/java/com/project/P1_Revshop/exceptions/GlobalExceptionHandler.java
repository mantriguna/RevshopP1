package com.project.P1_Revshop.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
        logger.error("An error occurred: ", ex);
        model.addAttribute("exception", ex);
        return "Error_Page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(Exception ex, HttpServletRequest request, Model model) {
        logger.error("404 Not Found: ", ex);
        model.addAttribute("exception", ex);
        return "Error_Page";
    }
}
