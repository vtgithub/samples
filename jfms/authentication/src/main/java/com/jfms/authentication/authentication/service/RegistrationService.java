package com.jfms.authentication.authentication.service;

import com.jfms.authentication.authentication.api.model.UserRegistrationRequest;
import com.jfms.authentication.authentication.converter.UserConverter;
import com.jfms.authentication.authentication.dal.entity.UserEntity;
import com.jfms.authentication.authentication.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    ActivationService activationService;

    public void register(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = userConverter.getEntity(userRegistrationRequest);
        userRepository.save(userEntity);
        activationService.generateActivationCode(
                userRegistrationRequest.getActivationCodeLength(),
                userRegistrationRequest.getMobileNumber()
        );
    }
}
