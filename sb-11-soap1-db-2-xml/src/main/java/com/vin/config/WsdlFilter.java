package com.vin.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WsdlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Handle WCF-style ?wsdl
        if ("wsdl".equalsIgnoreCase(req.getQueryString())
                && req.getRequestURI().equals("/user-service.svc")) {

            request.getRequestDispatcher("/user-service.svc/user-service.wsdl")
                   .forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}