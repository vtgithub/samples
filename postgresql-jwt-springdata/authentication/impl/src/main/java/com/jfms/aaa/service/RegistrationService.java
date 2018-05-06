package com.jfms.aaa.service;


import com.jfms.aaa.converter.UserConverter;
import com.jfms.aaa.dal.entity.UserEntity;
import com.jfms.aaa.dal.repository.UserRepository;
import com.jfms.aaa.model.UserRegistrationRequest;
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

    public void register(UserRegistrationRequest userRegistrationRequest) throws TooRequestException {
        UserEntity userEntity = userConverter.getEntity(userRegistrationRequest);
        userRepository.save(userEntity);
        activationService.generateActivationCode(
                userRegistrationRequest.getActivationCodeLength(),
                userRegistrationRequest.getMobileNumber()
        );
    }
}
