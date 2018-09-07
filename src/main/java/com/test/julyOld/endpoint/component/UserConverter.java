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
        String name = userDto.getName();
        String userName = userDto.getUserName();
        String password = userDto.getPassword();
        String roleName = userDto.getRoleName();
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setName(name);
        userCreationRequest.setUserName(userName);
        userCreationRequest.setPassword(password);
        userCreationRequest.setRoleName(roleName);
        return userCreationRequest;
    }

    public UserDto convertEntityToDto(final User user){
        Long id = user.getId();
        String name = user.getName();
        String userName = user.getUserName();
        String password = user.getPassword();
        String roleName = user.getRole().getRoleName();
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRoleName(roleName);
        return userDto;
    }

    public List<UserDto> convertEntityToDtoList(final List<User> users){
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add(convertEntityToDto(user));
        }
        return userDtoList;
    }
}
