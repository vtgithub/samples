package dev.springframework.converters;

import dev.springframework.commands.UserDto;
import dev.springframework.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class UserConverter {

    public UserDto getDto(User user) {
        if (user == null)
            return null;
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setDescription(user.getDescription());
        userDto.setSalary(user.getSalary());
        userDto.setPassword(user.getPassword());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public List<UserDto> getDtoList(List<User> userList){
        if (userList == null)
            return null;
        List<UserDto> userDtoList =  new ArrayList<UserDto>();
        for (User user : userList){
            userDtoList.add(getDto(user));
        }
        return userDtoList;
    }

    public User getEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setSalary(userDto.getSalary());
        user.setDescription(userDto.getDescription());
        return user;
    }


}
