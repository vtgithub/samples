package com.jfms.authentication.authentication.filter;

import com.jfms.authentication.authentication.service.AuthenticationService;
import com.jfms.authentication.authentication.service.biz.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements Filter{

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authToken = httpServletRequest.getHeader("Auth");
        String requestURI = String.valueOf(httpServletRequest.getRequestURL());
        if (authenticationService.isValidToken(authToken) || authenticationService.isAuthenticatedURI(requestURI)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        httpServletResponse.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
    }

    @Override
    public void destroy() {
    }
}
