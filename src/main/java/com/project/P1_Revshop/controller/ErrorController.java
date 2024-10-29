package com.project.P1_Revshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, HttpServletRequest request, Model model) {
        model.addAttribute("exception", ex);
        return "Error_Page"; // This will point to your Thymeleaf error template
    }
}
