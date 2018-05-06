package com.jfms.aaa;

import com.jfms.aaa.model.ActivationCodeRequest;
import com.jfms.aaa.model.UserActivationRequest;
import com.jfms.aaa.model.UserActivationResponse;
import com.jfms.aaa.model.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/aaa/user", produces = "application/json", consumes = "application/json")
public interface UserApi {

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    void registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest, HttpServletResponse response);

    @RequestMapping(method = RequestMethod.POST, value = "/activate")
    UserActivationResponse activateUser(@RequestBody UserActivationRequest userActivationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/activation/code")
    void activationCode(@RequestBody ActivationCodeRequest activationCodeRequest, HttpServletResponse response);

    @RequestMapping(method = RequestMethod.GET, value = "/reactivate")
    UserActivationResponse doReactivate(@RequestHeader("auth") String oldToken);
}
