package com.project.P1_Revshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgetPasswordController {

    private final String correctOtp = "123456"; 
    @GetMapping("/customer/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password"; 
    }

    @PostMapping("/send-otp")
    public ModelAndView sendOtp(@RequestParam("email") String email) {
        if (email.endsWith("@gmail.com")) {
         
            ModelAndView mav = new ModelAndView("otp-verification");
            mav.addObject("email", email);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("forgot-password");
            mav.addObject("error", "Please enter a valid Gmail address.");
            return mav;
        }
    }

    @PostMapping("/verify-otp")
    public ModelAndView verifyOtp(@RequestParam("otp") String otp) {
        if (otp.equals(correctOtp)) {
            return new ModelAndView("login-success"); // Redirect to success page
        } else {
            ModelAndView mav = new ModelAndView("otp-verification");
            mav.addObject("error", "Invalid OTP. Please try again.");
            return mav;
        }
    }

    @GetMapping("/login-success")
    public String loginSuccessPage() {
        return " redriect:/buyerdashboard";
    }
}
