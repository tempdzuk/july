package com.test.julyOld.endpoint;


import com.test.julyOld.endpoint.component.UserConverter;
import com.test.julyOld.endpoint.model.UserDto;
import com.test.julyOld.entity.User;
import com.test.julyOld.service.impl.UserServiceImpl;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserEndpoint {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<UserDto> getAllUsers(){
        final List<User> users = userService.findAll();
        return userConverter.convertEntityToDtoList(users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byRole/{roleName}")
    public List<UserDto> getAllUsersByRoleName(@PathVariable final String roleName){
        final List<User> users = userService.getUsersByRole(roleName);
        return userConverter.convertEntityToDtoList(users);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public UserDto create(@RequestBody final UserDto userDto){
        final UserCreationRequest userCreationRequest = userConverter.convertDtoToRequest(userDto);
        final User createdUser = userService.create(userCreationRequest);
        return userConverter.convertEntityToDto(createdUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    public UserDto update(@RequestBody final UserDto userDto){
        final User updatedUser = userService.update(userDto);
        return userConverter.convertEntityToDto(updatedUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public UserDto getUserByUserName(@PathVariable final String userName){
        final User user = userService.getUserByUserName(userName);
        return userConverter.convertEntityToDto(user);
    }



}
