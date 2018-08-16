package ir.ord.application.filters;

import ir.ord.application.accessories.Helper;
import javassist.bytecode.ByteArray;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

/**
 * Created by vahid on 7/15/17.
 */
@WebFilter(filterName = "authenticationFilter",asyncSupported = true)
public class AuthenticationFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        CustomHttpServletRequestWrapper servletRequestWrapper =
                new CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);

        ServletInputStream servletInputStream = servletRequestWrapper.getInputStream();
        byte[] receivedBody = Helper.getByteArrayFromServletInputStream(servletInputStream);

        try {
            byte[] decryptedBody = Helper.AES.decrypt(
                    new String( Helper.RSA.decrypt(servletRequestWrapper.getHeader("AK"))),
                    Helper.AES.initVector,
                    receivedBody
            );
            servletRequestWrapper.setBody(decryptedBody);

            filterChain.doFilter(servletRequestWrapper, servletResponse);

            CustomHttpServletResponseWrapper servletResponseWrapper =
                    new CustomHttpServletResponseWrapper((HttpServletResponse) servletResponse);
            String randomAESKey = Helper.AES.getRandomKey();
            servletResponseWrapper.setHeader("AK", Helper.RSA.encrypt(randomAESKey.getBytes()));

            byte[] responseBody = servletResponseWrapper.getBodyCopy();
            responseBody = Helper.AES.encrypt(
                    randomAESKey,
                    Helper.AES.initVector,
                    responseBody
            );
            servletResponseWrapper.getResponse().resetBuffer();
            servletResponseWrapper.getResponse().getOutputStream().write(responseBody);
        } catch (Exception e) {
            Helper.appLogger.error("AuthenticationFilter", e);
        }


    }


    public void destroy() {

    }


}
