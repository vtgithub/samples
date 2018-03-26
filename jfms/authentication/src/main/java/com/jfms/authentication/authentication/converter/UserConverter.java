package com.jfms.authentication.authentication.converter;

import com.jfms.authentication.authentication.api.model.UserRegistration;
import com.jfms.authentication.authentication.dal.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity getEntity(UserRegistration userRegistration){
        if (userRegistration == null)
            return null;
        return new UserEntity(
                userRegistration.getMobileNumber(),
                userRegistration.getFirstName(),
                userRegistration.getLastName()
        );
    }

}
