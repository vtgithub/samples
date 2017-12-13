package servlets;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by vahid on 8/20/17.
 */
public class ServletForward implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servletForward initialized");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Boolean forward = Boolean.parseBoolean(servletRequest.getParameter("forward"));
        System.out.println("forward:"+forward);
        RequestDispatcher requestDispatcher;
        if (forward){
            requestDispatcher = servletRequest.getRequestDispatcher("/static-test");
            System.out.println("forwarded to static-page.html");
            requestDispatcher.forward(servletRequest, servletResponse);
            servletResponse.getWriter().write("this is from main not included servlet");

        }else {
            servletResponse.setContentType("text/html");
            servletResponse.getWriter().write("<---included--->");
            requestDispatcher = servletRequest.getRequestDispatcher("pages/include-page.html");
            System.out.println("include include-page.html");
            requestDispatcher.include(servletRequest, servletResponse);
            servletResponse.getWriter().write("this is from main not included servlet");
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("servletForward destroyed");
    }
}
