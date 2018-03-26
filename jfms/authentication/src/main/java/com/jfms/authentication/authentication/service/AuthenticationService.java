package com.jfms.authentication.authentication.service;

import com.jfms.authentication.authentication.service.biz.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    JWT jwt;

    public Boolean isValidToken(String token){
        try{
            jwt.parseJWT(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean isAuthenticatedURI(String uri){
        if (
                uri.contains("aaa/user/register") ||
                uri.contains("aaa/user/activate") ||
                uri.contains("aaa/user/reactivate")
           ){

            return true;
        }
        return false;
    }
}
