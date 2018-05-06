package com.jfms.aaa.converter;


import com.jfms.aaa.dal.entity.UserEntity;
import com.jfms.aaa.model.UserRegistrationRequest;
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
