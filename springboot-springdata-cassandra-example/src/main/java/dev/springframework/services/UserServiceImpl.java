package dev.springframework.services;

import dev.springframework.commands.UserDto;
import dev.springframework.domain.User;
import dev.springframework.repositories.UserRepository;
import dev.springframework.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;


    @Override
    public List<UserDto> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        List<UserDto> dtoList = userConverter.getDtoList(users);
        return dtoList;
    }

    @Override
    public UserDto getById(UUID id) {
//        return userRepository.findById(id).orElse(null);
        UserDto userDto = userConverter.getDto(userRepository.findById(id).get());
        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User entity = userConverter.getEntity(userDto);
        entity.setId(UUID.randomUUID());
        if (entity == null)
            return null;
        userRepository.save(entity);
        userDto.setId(entity.getId());
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (userDto == null || userDto.getId() == null)
            return null;
        User user = userRepository.findById(userDto.getId()).get();
        if (user == null)
            return null;
        user = userConverter.getEntity(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);

    }

}
