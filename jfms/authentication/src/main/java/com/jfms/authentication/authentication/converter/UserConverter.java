package com.jfms.authentication.authentication.converter;

import com.jfms.authentication.authentication.api.model.UserRegistrationRequest;
import com.jfms.authentication.authentication.dal.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity getEntity(UserRegistrationRequest userRegistrationRequest){
        if (userRegistrationRequest == null)
            return null;
        return new UserEntity(
                userRegistrationRequest.getMobileNumber(),
                userRegistrationRequest.getFirstName(),
                userRegistrationRequest.getLastName()
        );
    }

}
