package com.project.P1_Revshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FaviconController {
    @RequestMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
        // Do nothing or return a 204 No Content response
    }
}
