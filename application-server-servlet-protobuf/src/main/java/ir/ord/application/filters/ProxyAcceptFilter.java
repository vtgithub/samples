package ir.ord.application.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vahid on 4/16/17.
 */
public class ProxyAcceptFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("proxy-accept filter .....");

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        System.out.println(httpServletRequest.getAttribute("key"));
        filterChain.doFilter(httpServletRequest, httpServletResponse);

        //TODO app processes
        httpServletResponse.sendRedirect("http://proxy-server:81/proxy-war/app/");
    }

    public void destroy() {

    }
}
