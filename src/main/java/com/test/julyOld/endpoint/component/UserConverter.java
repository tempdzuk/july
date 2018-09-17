package com.test.julyOld.endpoint.component;

import com.test.julyOld.endpoint.model.UserDto;
import com.test.julyOld.entity.User;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public UserCreationRequest convertDtoToRequest(final UserDto userDto){
        final String name = userDto.getName();
        final String userName = userDto.getUserName();
        final String password = userDto.getPassword();
        final String roleName = userDto.getRoleName();
        final UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setName(name);
        userCreationRequest.setUserName(userName);
        userCreationRequest.setPassword(password);
        userCreationRequest.setRoleName(roleName);
        return userCreationRequest;
    }

    public UserDto convertEntityToDto(final User user){
        final Long id = user.getId();
        final String name = user.getName();
        final String userName = user.getUserName();
        final String password = user.getPassword();
        final String roleName = user.getRole().getRoleName();
        final UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRoleName(roleName);
        return userDto;
    }

    public List<UserDto> convertEntityToDtoList(final List<User> users){
        final List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add(convertEntityToDto(user));
        }
        return userDtoList;
    }
}
