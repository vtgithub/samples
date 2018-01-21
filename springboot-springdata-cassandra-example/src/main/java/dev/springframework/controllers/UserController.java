package dev.springframework.controllers;

import dev.springframework.commands.UserDto;
import dev.springframework.converters.UserConverter;
import dev.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity listProducts(){
        List<UserDto> userDtoList = userService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}" , produces = "application/json")
    public ResponseEntity getProduct(@PathVariable String id){
        UserDto userDto = userService.getById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity edit(@RequestBody UserDto userDto){
        UserDto dto = userService.update(userDto);
        if (dto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity newProduct(@RequestBody UserDto userDto){
        UserDto dto = userService.save(userDto);
        if (dto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable String id){
        userService.delete(UUID.fromString(id));
        return ResponseEntity.ok().body(null);
    }
}
