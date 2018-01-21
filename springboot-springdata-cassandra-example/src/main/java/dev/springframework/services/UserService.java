package dev.springframework.services;

import dev.springframework.commands.UserDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
public interface UserService {

    List<UserDto> listAll();

    UserDto getById(UUID id);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    void delete(UUID id);

}
