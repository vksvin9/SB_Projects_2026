package com.vin.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WsdlRedirectController {

    @GetMapping("/user-service.svc")
    public String redirectToWsdl(HttpServletRequest request) {

        if ("wsdl".equalsIgnoreCase(request.getQueryString())) {
            return "redirect:/user-service.svc/users.wsdl";
        }

        return "forward:/";
    }
}