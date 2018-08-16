package ir.ord.application.filters;

import ir.ord.application.ResponseStatus;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.LogMessages;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.SessionBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.SessionInfoDao;
import ir.ord.application.dal.entities.SessionInfoEntity;
import org.apache.log4j.Logger;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vahid on 5/3/17.
 */
@WebFilter(filterName = "authorizationFilter", asyncSupported = true)
public class AuthorizationFilter  implements Filter {

    @Inject
    private SessionBiz sessionBiz;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, Accept, x-auth-token, "
                + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");


        String acceptHeader = httpServletRequest.getHeader("Accept").toLowerCase().trim();

        String requestURI = String.valueOf(httpServletRequest.getRequestURL());
        if( requestURI.contains("/log/") || requestURI.contains("/test")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // check wrong Accept header
        if((acceptHeader == null || (!acceptHeader.toLowerCase().equals("application/json") && !acceptHeader.toLowerCase().equals("application/octet-stream"))) && !requestURI.contains("application-war/rest/payment/fromUser") ){
            httpServletResponse.setStatus(ResponseStatus.BAD_REQUEST.getCode());
//            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        if (requestURI.contains("/signUp") ||
                requestURI.contains("/signIn") ||
                requestURI.contains("account/activation") ||
                requestURI.contains("/button" ) ||
                requestURI.contains("/pages")||
                requestURI.contains("/followup/order") ||
                requestURI.contains("/test") ||
                requestURI.contains("/deploy/wars") ||
                requestURI.contains("/management") ||
                requestURI.contains("/category") ||
                requestURI.contains("/timePeriod") ||
                requestURI.contains("/package") ||
                requestURI.contains("/payment/fromUser")){

            filterChain.doFilter(httpServletRequest, httpServletResponse);
//            System.out.println("after chain ....");
            return;
        }

        String sessionId = httpServletRequest.getHeader("sessionId");
        try {
            Boolean isActive  = sessionBiz.isSessionActive(sessionId);
            if (!isActive){
                httpServletResponse.setStatus(ResponseStatus.UNAUTHORIZED.getCode());
                Helper.appLogger.info(LogMessages.IllegalUserSession);
                httpServletResponse.getWriter().println(ResponseMessages.unauthorizedAccount);
            }else {
                sessionBiz.updateSessionTime(sessionId);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } catch (DaoException e) {
            Helper.appLogger.error("authorizationFilter", e);
            httpServletResponse.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            httpServletResponse.getWriter().println(ResponseMessages.unknownFailure);
        } catch (JedisConnectionException e){
            Helper.appLogger.error("authorizationFilter", e);
            DaoHelper.reconnectRedis();
            httpServletResponse.getWriter().println(ResponseMessages.unknownFailure);
        } catch (Exception e){
            Helper.appLogger.error("authorizationFilter", e);
        }
    }

    public void destroy() {

    }
}
