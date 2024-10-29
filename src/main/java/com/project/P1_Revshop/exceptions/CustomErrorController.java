package com.project.P1_Revshop.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(WebRequest request, Map<String, Object> model) {
        // Get error attributes and add them to the model
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        model.putAll(errorAttributes);
        return "Error_Page"; // Name of your error template
    }

    
    public String getErrorPath() {
        return "/error";
    }
}
