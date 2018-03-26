package com.jfms.authentication.authentication.api;


import com.jfms.authentication.authentication.api.model.UserActivationRequest;
import com.jfms.authentication.authentication.api.model.UserActivationResponse;
import com.jfms.authentication.authentication.api.model.UserRegistration;
import com.jfms.authentication.authentication.service.ActivationService;
import com.jfms.authentication.authentication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/aaa/user", produces = "application/json", consumes = "application/json")
public class RegistrationApi {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ActivationService activationService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity registerUser(@RequestBody UserRegistration userRegistration){
        try{
            registrationService.register(userRegistration);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activate")
    public ResponseEntity activateUser(@RequestBody UserActivationRequest userActivationRequest){
        try{
            UserActivationResponse userActivationResponse = activationService.activateUser(userActivationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(userActivationResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reactivate")
    public ResponseEntity doReactivate(@RequestBody UserActivationResponse oldToken){
        try {
            UserActivationResponse userActivationResponse = activationService.getNewToken(oldToken.getToken());
            return ResponseEntity.status(HttpStatus.OK).body(userActivationResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
