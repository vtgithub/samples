package com.jfms.authentication.authentication.service;

import com.jfms.authentication.authentication.api.model.UserActivationRequest;
import com.jfms.authentication.authentication.api.model.UserActivationResponse;
import com.jfms.authentication.authentication.api.model.UserRegistration;
import com.jfms.authentication.authentication.dal.entity.ActivationEntity;
import com.jfms.authentication.authentication.dal.repository.ActivationRepository;
import com.jfms.authentication.authentication.service.biz.JWT;
import com.jfms.authentication.authentication.service.biz.RandomGenerator;
import com.jfms.authentication.authentication.service.biz.SMSService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ActivationService {
    @Autowired
    JWT jwt;
    @Autowired
    ActivationRepository activationRepository;
    @Autowired
    RandomGenerator randomGenerator;
    @Autowired
    SMSService smsService;

    private @Value("${token.ttl}") Long tokenTtl;

    public UserActivationResponse activateUser(UserActivationRequest userActivationRequest) {

        ActivationEntity activationEntity =
                activationRepository.findActivationEntityByActivationCodeAndIsUsedAndMobileNumber(
                userActivationRequest.getActivationCode(),
                false,
                userActivationRequest.getMobileNumber()
        );
        activationEntity.setUsed(true);
        activationRepository.save(activationEntity);
        String jwtToken = jwt.createJWTToken(tokenTtl, null);
        return new UserActivationResponse(jwtToken);
    }

    public UserActivationResponse getNewToken(String token) {
        try{
            jwt.parseJWT(token);
//            String jwtToken = jwt.createJWTToken(tokenTtl, null);
            return new UserActivationResponse(token);
        }catch (ExpiredJwtException e){
            String jwtToken = jwt.createJWTToken(tokenTtl, null);
            return new UserActivationResponse(jwtToken);
        }
    }
}
