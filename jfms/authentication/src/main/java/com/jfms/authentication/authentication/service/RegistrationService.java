package com.jfms.authentication.authentication.service;

import com.jfms.authentication.authentication.api.model.UserRegistration;
import com.jfms.authentication.authentication.converter.UserConverter;
import com.jfms.authentication.authentication.dal.entity.ActivationEntity;
import com.jfms.authentication.authentication.dal.entity.UserEntity;
import com.jfms.authentication.authentication.dal.repository.ActivationRepository;
import com.jfms.authentication.authentication.dal.repository.UserRepository;
import com.jfms.authentication.authentication.service.biz.RandomGenerator;
import com.jfms.authentication.authentication.service.biz.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    SMSService smsService;
    @Autowired
    RandomGenerator randomGenerator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    ActivationRepository activationRepository;

    public void register(UserRegistration userRegistration) {
        UserEntity userEntity = userConverter.getEntity(userRegistration);
        userRepository.save(userEntity);
        String activationCode = randomGenerator.getRandomNumber(userRegistration.getActivationCodeLength());
        activationRepository.save(new ActivationEntity(activationCode, userRegistration.getMobileNumber()));
        smsService.sendText(userRegistration.getMobileNumber(), activationCode);
    }
}
